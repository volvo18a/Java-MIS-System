package dao;

import java.util.List;

import bean.Monthreport;

public interface MonthReportDao {
	public List<Monthreport> getAllReport(String date1,String date2);
}
