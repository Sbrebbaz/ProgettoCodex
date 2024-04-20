package ui;

import base.Color;
import base.Player;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class PlayerCreation {

	protected Shell shlPlayerCreation;
	private Text PlayerNameTextBox;
	
	private Player player;

	public PlayerCreation(int playerIDToCreate) {
		player = new Player(playerIDToCreate, "" , Color.BLUE);
	}

	public Player getPlayer() {
		return player;
	}

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			PlayerCreation window = new PlayerCreation(-1);
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
		shlPlayerCreation.open();
		shlPlayerCreation.layout();
		while (!shlPlayerCreation.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlPlayerCreation = new Shell();
		shlPlayerCreation.setSize(1028, 561);
		shlPlayerCreation.setText("PLAYER CREATION");
		shlPlayerCreation.setLayout(new FillLayout(SWT.VERTICAL));

		Composite composite = new Composite(shlPlayerCreation, SWT.NONE);
		FillLayout fl_composite = new FillLayout(SWT.VERTICAL);
		fl_composite.spacing = 200;
		composite.setLayout(fl_composite);

		PlayerNameTextBox = new Text(composite, SWT.BORDER);

		Button ConfirmButton = new Button(composite, SWT.NONE);
		ConfirmButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				player.setName(PlayerNameTextBox.getText());
				shlPlayerCreation.close();
			}
		});
		ConfirmButton.setText("Confirm");

	}

}
