package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBConnect implements AutoCloseable {
	private Connection con;
	public DBConnect() throws SQLException {
		String url = "jdbc:ucanaccess://Words.accdb";


		con = DriverManager.getConnection(url);


	}
	/**
	 * 単語リストを返す
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<WordSet> SelectWords() throws SQLException {
		String SQL = "SELECT * FROM Word order by Key desc";
		ArrayList<WordSet> tmpList = new ArrayList<>();
		ResultSet rs = con.prepareStatement(SQL).executeQuery();
		while(rs.next()) {
			tmpList.add(new WordSet(rs.getString("Key"), rs.getString("Val")));
		}
		return tmpList;
	}
	/**
	 * パーツセットを返す
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<WordSet> SelectParts() throws SQLException {
		String SQL = "SELECT * FROM Parts order by Key desc";
		ArrayList<WordSet> tmpList = new ArrayList<>();
		ResultSet rs = con.prepareStatement(SQL).executeQuery();
		while(rs.next()) {
			tmpList.add(new WordSet(rs.getString("Key"), rs.getString("Val")));
		}
		return tmpList;
	}
	@Override
	public void close() throws Exception {
		// TODO 自動生成されたメソッド・スタブ
		con.close();
	}
}
