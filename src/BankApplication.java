import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.*;
import net.miginfocom.swing.MigLayout;

public class BankApplication extends JFrame {
	
	ArrayList<BankAccount> accountList = new ArrayList<BankAccount>();
	static HashMap<Integer, BankAccount> table = new HashMap<Integer, BankAccount>();
	final static int TABLE_SIZE = 29;
	
	MenuNavigate Navigate = new MenuNavigate();
	MenuRecord recordsMenu = new MenuRecord();
	MenuTransaction transactionsMenu = new MenuTransaction();
	MenuFile fileMenu = new MenuFile();

	JMenuBar menuBar;
	JMenu exitMenu;
	JMenuItem closeApp;
	
	JLabel accountIDLabel, accountNumberLabel, firstNameLabel, surnameLabel, accountTypeLabel, balanceLabel, overdraftLabel;
	static JTextField accountIDTextField, accountNumberTextField, firstNameTextField ,surnameTextField, accountTypeTextField, balanceTextField, overdraftTextField ;
	
	static JFileChooser fc;
	JTable jTable;
	static double interestRate;
	
	static int currentItem = 0;
	
	static boolean openValues;
	
	public BankApplication() {
		
		super("Bank Application");
		
		initComponents();
	}
	
	public void initComponents() {
		setLayout(new BorderLayout());
		JPanel displayPanel = new JPanel(new MigLayout());
		
		accountIDLabel = new JLabel("Account ID: ");
		accountIDTextField = new JTextField(15);
		accountIDTextField.setEditable(false);
		
		displayPanel.add(accountIDLabel, "growx, pushx");
		displayPanel.add(accountIDTextField, "growx, pushx, wrap");
		
		accountNumberLabel = new JLabel("Account Number: ");
		accountNumberTextField = new JTextField(15);
		accountNumberTextField.setEditable(false);
		
		displayPanel.add(accountNumberLabel, "growx, pushx");
		displayPanel.add(accountNumberTextField, "growx, pushx, wrap");

		surnameLabel = new JLabel("Last Name: ");
		surnameTextField = new JTextField(15);
		surnameTextField.setEditable(false);
		
		displayPanel.add(surnameLabel, "growx, pushx");
		displayPanel.add(surnameTextField, "growx, pushx, wrap");

		firstNameLabel = new JLabel("First Name: ");
		firstNameTextField = new JTextField(15);
		firstNameTextField.setEditable(false);
		
		displayPanel.add(firstNameLabel, "growx, pushx");
		displayPanel.add(firstNameTextField, "growx, pushx, wrap");

		accountTypeLabel = new JLabel("Account Type: ");
		accountTypeTextField = new JTextField(5);
		accountTypeTextField.setEditable(false);
		
		displayPanel.add(accountTypeLabel, "growx, pushx");
		displayPanel.add(accountTypeTextField, "growx, pushx, wrap");

		balanceLabel = new JLabel("Balance: ");
		balanceTextField = new JTextField(10);
		balanceTextField.setEditable(false);
		
		displayPanel.add(balanceLabel, "growx, pushx");
		displayPanel.add(balanceTextField, "growx, pushx, wrap");
		
		overdraftLabel = new JLabel("Overdraft: ");
		overdraftTextField = new JTextField(10);
		overdraftTextField.setEditable(false);
		
		displayPanel.add(overdraftLabel, "growx, pushx");
		displayPanel.add(overdraftTextField, "growx, pushx, wrap");
		
		add(displayPanel, BorderLayout.CENTER);
		
		JPanel buttonPanel = new JPanel(new GridLayout(1, 4));
		
		buttonPanel.add(Navigate.getFirstItemButton());
		buttonPanel.add(Navigate.getPrevItemButton());
		buttonPanel.add(Navigate.getNextItemButton());
		buttonPanel.add(Navigate.getLastItemButton());
		
		add(buttonPanel, BorderLayout.SOUTH);
		
		menuBar = new JMenuBar();
    	setJMenuBar(menuBar);
		
    	menuBar.add(Navigate.getNavigateMenu());
    	menuBar.add(recordsMenu.getRecordsMenu());
    	menuBar.add(fileMenu.getFileMenu());
    	menuBar.add(transactionsMenu.getTransactionsMenu());
    	
    	exitMenu = new JMenu("Exit");
    	
    	closeApp = new JMenuItem("Close Application");
    	
    	exitMenu.add(closeApp);
    	
    	menuBar.add(exitMenu);
    	
    	setDefaultCloseOperation(EXIT_ON_CLOSE);
    	
	
		recordsMenu.getOverdraft().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				MenuRecord.overdraftSetting();
			
			}
		});
	
		ActionListener first = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Navigate.firstItemInList();
			}
		};
		
		ActionListener last = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Navigate.lastItemInList();
			}
		};
		
		ActionListener next = new ActionListener() {
			public void actionPerformed(ActionEvent e){
				Navigate.nextItem();
			}
				
		};
		
		ActionListener prev = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Navigate.prevItem();
			}
		};
		
		
		Navigate.getNextItemButton().addActionListener(next);
		Navigate.getNextItem().addActionListener(next);

		Navigate.getPrevItemButton().addActionListener(prev);
		Navigate.getPrevItem().addActionListener(prev);

		Navigate.getFirstItemButton().addActionListener(first);
		Navigate.getFirstItem().addActionListener(first);

		Navigate.getLastItemButton().addActionListener(last);
		Navigate.getLastItem().addActionListener(last);
		
		recordsMenu.getDeleteItem().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				MenuRecord.deleteTheItem();
			}
		});
		
		recordsMenu.getCreateItem().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				MenuRecord.createItem();	
			}
		});
		
		
		recordsMenu.getModifyItem().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				MenuRecord.modifyTheItem();
			}
		});
		
		recordsMenu.getInterest().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				MenuRecord.setInterestRate();
			}
		});
		
		Navigate.getListAll().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Navigate.listAllItems();
			}
		});
		
		fileMenu.getOpen().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				MenuFile.openAFile();
			}
		});
		
		fileMenu.getSave().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				MenuFile.saveFile();
			}
		});
		
		fileMenu.getSaveAs().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				MenuFile.saveFileAs();
			}
		});
		
		closeApp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeApp();
			}
		});	
		
		Navigate.getFindBySurname().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Navigate.findSurname();
			}
		});
		
		Navigate.getFindByAccount().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Navigate.findAccount();
			}
		});
		
		transactionsMenu.getDeposit().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				MenuTransaction.accountDeposit();
			}
		});
		
		transactionsMenu.getWithdraw().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				MenuTransaction.accountWithdraw();
			}
		});
		
		transactionsMenu.getCalcInterest().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				MenuTransaction.calculateInterest();
			}
		});		
	}
	
	
	public static void saveOpenValues(){		
		if (openValues){
			surnameTextField.setEditable(false);
			firstNameTextField.setEditable(false);
				
			table.get(currentItem).setSurname(surnameTextField.getText());
			table.get(currentItem).setFirstName(firstNameTextField.getText());
		}
	}	
	
	 public static void displayDetails(int currentItem) {

	        accountIDTextField.setText(table.get(currentItem).getAccountID() + "");
	        accountNumberTextField.setText(table.get(currentItem).getAccountNumber());
	        surnameTextField.setText(table.get(currentItem).getSurname());
	        firstNameTextField.setText(table.get(currentItem).getFirstName());
	        accountTypeTextField.setText(table.get(currentItem).getAccountType());
	        balanceTextField.setText(table.get(currentItem).getBalance() + "");
	        if (accountTypeTextField.getText().trim().equals("Current"))
	            overdraftTextField.setText(table.get(currentItem).getOverdraft() + "");
	        else
	            overdraftTextField.setText("Only applies to current accs");
	    }

	private void closeApp()
	{
		int answer = JOptionPane.showConfirmDialog(BankApplication.this, "Do you want to save before quitting?");
		if (answer == JOptionPane.YES_OPTION) {
			MenuFile.saveFileAs();
			dispose();
		}
		else if(answer == JOptionPane.NO_OPTION)
		{
			dispose();
		}
	}
	
	public static void main(String[] args) {
		BankApplication ba = new BankApplication();
		ba.setSize(1200,400);
		ba.pack();
		ba.setVisible(true);
	}
	
	
}