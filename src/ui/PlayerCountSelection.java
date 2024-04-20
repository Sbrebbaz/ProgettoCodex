package ui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class PlayerCountSelection {

	private int playerCount;
	
	public int getPlayerCount() {
		return playerCount;
	}
	
	protected Shell shlPlayerCountSelection;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			PlayerCountSelection window = new PlayerCountSelection();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlPlayerCountSelection.open();
		shlPlayerCountSelection.layout();
		while (!shlPlayerCountSelection.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlPlayerCountSelection = new Shell();
		shlPlayerCountSelection.setSize(450, 150);
		shlPlayerCountSelection.setText("PLAYER COUNT SELECTION");
		shlPlayerCountSelection.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Button TwoPlayersButton = new Button(shlPlayerCountSelection, SWT.NONE);
		TwoPlayersButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				playerCount = 2;
				shlPlayerCountSelection.close();	
			}
		});
		TwoPlayersButton.setText("TWO");
		
		Button ThreePlayersButton = new Button(shlPlayerCountSelection, SWT.NONE);
		ThreePlayersButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				playerCount = 3;
				shlPlayerCountSelection.close();	
			}
		});
		ThreePlayersButton.setText("THREE");
		
		Button FourPlayersButton = new Button(shlPlayerCountSelection, SWT.NONE);
		FourPlayersButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				playerCount = 4;
				shlPlayerCountSelection.close();	
			}
		});
		FourPlayersButton.setText("FOUR");

	}

}
