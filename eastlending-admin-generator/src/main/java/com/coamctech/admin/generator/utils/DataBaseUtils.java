package com.coamctech.admin.generator.utils;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.coamctech.admin.generator.bean.ModelProperty;
import com.coamctech.admin.generator.swing.tree.node.ColumnMeta;
import com.coamctech.admin.generator.swing.tree.node.TableMeta;

public abstract class DataBaseUtils {

	public static final Map<String, List<TableMeta>> findTables(
			ModelProperty modelProperty) {
		Map<String, List<TableMeta>> dbMap = new HashMap<String, List<TableMeta>>();
		List<TableMeta> list = new ArrayList<TableMeta>();
		Connection conn = DbConnection.getConnection(modelProperty);
		String catalog = "";
		ResultSet rs = null;
		try {
			DatabaseMetaData metaData = conn.getMetaData();
			catalog = conn.getCatalog();
			rs = metaData.getTables(catalog, null, null, new String[] { "TABLE" });
			while (rs.next()) {
				TableMeta tm = new TableMeta();
				tm.setSchemaName(catalog);
				tm.setTableName(rs.getString("TABLE_NAME"));
				tm.setComment(rs.getString("REMARKS"));
				tm.setColumns(findColumnsByTable(metaData,
						rs.getString("TABLE_NAME")));
				list.add(tm);
			}
			dbMap.put(catalog, list);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return dbMap;
	}

	private static final List<ColumnMeta> findColumnsByTable(
			DatabaseMetaData metaData, String tableName) {
		try {
			ResultSet rs = metaData.getColumns(null, null, tableName, null);
			ResultSet keyRs = metaData.getPrimaryKeys(null, null, tableName);
			String pkColumn = "";
			while (keyRs.next()) {
				pkColumn = keyRs.getString("COLUMN_NAME");
				break;
			}
			List<ColumnMeta> metaList = new ArrayList<ColumnMeta>();
			while (rs.next()) {
				ColumnMeta meta = new ColumnMeta();
				meta.setColumnName(rs.getString("COLUMN_NAME"));
				meta.setDataType(getColumnDataType(rs.getInt("DATA_TYPE")));
				if (null != pkColumn
						&& pkColumn.equals(rs.getString("COLUMN_NAME"))) {
					meta.setPrimaryKey(true);
				}
				if (null != rs.getString("IS_NULLABLE")
						&& rs.getString("IS_NULLABLE").equals("yes")) {
					meta.setNullable(true);
				} else {
					meta.setNullable(false);
				}
				meta.setColumnSize(rs.getInt("COLUMN_SIZE"));
				meta.setOrdinalPosition(rs.getString("ORDINAL_POSITION"));
				meta.setColumnDefault(rs.getString("COLUMN_DEF"));
				meta.setColumnComment(rs.getString("REMARKS"));
				metaList.add(meta);
			}

			Comparator<ColumnMeta> orderBy = (c1, c2) -> c1
					.getOrdinalPosition().compareTo(c2.getOrdinalPosition());
			metaList.sort(orderBy);
			return metaList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static String getColumnDataType(int type) {
		if (type == Types.CHAR || type == Types.VARCHAR
				|| type == Types.LONGVARCHAR || type == Types.BLOB
				|| type == Types.CLOB) {
			return "String";
		} else if (type == Types.NUMERIC || type == Types.DECIMAL) {
			return "java.math.BigDecimal";
		} else if (type == Types.BIT || type == Types.BOOLEAN) {
			return "Boolean";
		} else if (type == Types.TINYINT || type == Types.SMALLINT
				|| type == Types.INTEGER) {
			return "INTEGER";
		} else if (type == Types.BIGINT) {
			return "Long";
		} else if (type == Types.FLOAT) {
			return "FLOAT";
		} else if (type == Types.DOUBLE) {
			return "DOUBLE";
		} else if (type == Types.DATE || type == Types.TIME
				|| type == Types.TIMESTAMP) {
			return "java.sql.Date";
		}
		return "";
	}

	public static void main(String[] args) throws SQLException {
		ModelProperty modelProperty = new ModelProperty();
		modelProperty
				.setJdbcUrl("jdbc:mysql://localhost:3306/fisp?useUnicode=true&characterEncoding=GBK");
		modelProperty.setJdbcUser("root");
		modelProperty.setJdbcPass("root");
		DataBaseUtils.findTables(modelProperty);
	}

}
