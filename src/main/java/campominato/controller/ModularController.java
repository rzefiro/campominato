package campominato.controller;

public interface ModularController extends Controller
{
	public static final int SUBVIEW_SETUP_PARTITA = 0;
	public static final int SUBVIEW_PARTITA = 1;

	void mostraSubView(int subViewId);
}