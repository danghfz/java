package model.command;

/**
 * @author danghf
 * @version 1.0
 * 2022/11/18   9:32
 * JdbcTemplate
 */
public class Jdk {
    /**
     * class JdbcTemplate
     *      public Object query(final String sql, final ResultSetExtractor rse) throws DataAccessException {
     * 		Assert.notNull(sql, "SQL must not be null");
     * 		Assert.notNull(rse, "ResultSetExtractor must not be null");
     * 		if (logger.isDebugEnabled()) {
     * 			logger.debug("Executing SQL query [" + sql + "]");
     *                }
     *      //   QueryStatementCallback 命令接口 + 命令执行者
     * 		class QueryStatementCallback implements StatementCallback, SqlProvider {
     * 			public Object doInStatement(Statement stmt) throws SQLException {
     * 				ResultSet rs = null;
     * 				try {
     * 					rs = stmt.executeQuery(sql);
     * 					ResultSet rsToUse = rs;
     * 					if (nativeJdbcExtractor != null) {
     * 						rsToUse = nativeJdbcExtractor.getNativeResultSet(rs);
     *                    }
     * 					return rse.extractData(rsToUse);
     *                }
     * 				finally {
     * 					JdbcUtils.closeResultSet(rs);
     *                }
     *            }
     * 			public String getSql() {
     * 				return sql;
     *            }
     *        }
     *        // 执行 JdbcTemplate 充当命令调用者
     * 		return execute(new QueryStatementCallback());* 	}
     */
}
