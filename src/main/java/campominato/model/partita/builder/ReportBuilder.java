package campominato.model.partita.builder;

import campominato.model.partita.Report;
import campominato.model.partita.impl.ReportImpl;

public class ReportBuilder 
{
	public static ReportBuilder creaReportBuilder()
	{
		ReportBuilder builder = new ReportBuilder();
		
		return builder;
	}
	
	public Report build()
	{
		return new ReportImpl();
	}
}
