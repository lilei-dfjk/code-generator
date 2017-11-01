package com.coamctech.admin.generator.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.coamctech.admin.generator.bean.ModelProperty;

public abstract class DbConnection {
	private static Connection conn;

	public static final Connection getConnection(ModelProperty modelProperty) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			if (null == conn || conn.isClosed()) {
				conn = DriverManager.getConnection(modelProperty.getJdbcUrl(),
						modelProperty.getJdbcUser(),
						modelProperty.getJdbcPass());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return conn;
	}

	public static final void close() {
		if (null != conn) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
