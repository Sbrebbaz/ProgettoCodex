package ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class MainMenu {
	
	private boolean startGame = false;
	
	public boolean isStartGame() {
		return startGame;
	}

	protected Shell shlCodex;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			MainMenu window = new MainMenu();
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
		shlCodex.open();
		shlCodex.layout();
		while (!shlCodex.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlCodex = new Shell();
		shlCodex.setSize(937, 581);
		shlCodex.setText("Codex Naturalis");
		FillLayout fl_shlCodex = new FillLayout(SWT.VERTICAL);
		fl_shlCodex.spacing = 50;
		fl_shlCodex.marginHeight = 100;
		fl_shlCodex.marginWidth = 200;
		shlCodex.setLayout(fl_shlCodex);
		
		
		Label TitleLabel = new Label(shlCodex, SWT.CENTER);
		TitleLabel.setAlignment(SWT.CENTER);
		TitleLabel.setText("Codex Naturalis");
		
		Composite composite = new Composite(shlCodex, SWT.NONE);
		FillLayout fl_composite = new FillLayout(SWT.VERTICAL);
		fl_composite.spacing = 10;
		composite.setLayout(fl_composite);
		
		Button StartGameButton = new Button(composite, SWT.NONE);
		StartGameButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				//Set start game to true and close the form		
				startGame = true;
				shlCodex.close();				
			}
		});
		StartGameButton.setText("START GAME");
		
		Button QuitGameButton = new Button(composite, SWT.NONE);
		QuitGameButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				//Set start game to false and close the form
				startGame = false;
				shlCodex.close();	
			}
		});
		QuitGameButton.setText("QUIT");

	}
}
