package kadai_010;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Scores_Chapter10 {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Connection con = null;
		Statement statement = null;

		try {

			con = DriverManager.getConnection(
					"jdbc:mysql://localhost/challenge_java",
					"root",
					"Yorosiku7");			

			statement = con.createStatement();
			System.out.println("データベース接続成功"+ statement.toString());
			String updateSql = "UPDATE scores SET score_math = 95, score_english = 80 WHERE id = 5;";
			String orderSql = "SELECT * FROM scores ORDER BY score_math DESC, score_english DESC";

			System.out.println("レコード更新を実行します");
			statement.executeUpdate(updateSql);
			ResultSet result = statement.executeQuery(orderSql);
			System.out.println("1件のレコードが更新されました");
			System.out.println("数学・英語の点数が高い順に並べ替えました");

			while (result.next()) {
				int id = result.getInt("id");
				String name = result.getString("name");
				int score_math = result.getInt("score_math");
				int score_english = result.getInt("score_english");

				System.out.println(result.getRow() + "件目：生徒id=" + id
						+ "／氏名=" + name + "／数学=" + score_math + "/英語＝" + score_english);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("エラー発生：" + e.getMessage());
		} finally {
			// 使用したオブジェクトを解放
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException ignore) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ignore) {
				}
			}

		}
	}
}
