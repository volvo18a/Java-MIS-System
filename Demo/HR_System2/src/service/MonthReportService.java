package service;

import java.util.List;

import bean.Monthreport;
import dao.MonthReportDao;
import daoImpl.MonthReportDaoImpl;

public class MonthReportService {
	MonthReportDao monthreportDao = new MonthReportDaoImpl();

	public List<Monthreport> getAllReport(String date1, String date2) {
		return monthreportDao.getAllReport(date1, date2);
	}
}
