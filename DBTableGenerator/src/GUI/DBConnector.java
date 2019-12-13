package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.*;
import java.util.Arrays;
import java.util.Enumeration;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.border.BevelBorder;
import javax.swing.AbstractButton;
import javax.swing.AbstractListModel;
import javax.swing.ButtonGroup;

import java.awt.Button;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.URL;
import java.awt.event.ActionEvent;
import connectors.ConnectionFactory;
import javax.swing.JTextArea;
import javax.swing.JSpinner;
import javax.swing.SwingConstants;
import javax.swing.SpinnerNumberModel;

public class DBConnector extends JFrame {

	private JPanel contentPane;
	private JTextField URLField;
	private JTextField portField;
	private JLabel lblUser;
	private JLabel lblPassword;
	private JTextField userField;
	private JPasswordField passwordField;
	private JTable table;
	private JTextField txtName;
	private static JTextField LengthField;
	private JTextField DBNamefield;
	private static JSpinner spinner;
	private static JList list;
	private JTextField TableName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DBConnector frame = new DBConnector();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DBConnector() {
		setTitle("DataBase Auto Table Generator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 670, 547);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblUrlport = new JLabel("URL&Port");
		lblUrlport.setBounds(12, 10, 57, 15);
		contentPane.add(lblUrlport);

		URLField = new JTextField();
		URLField.setBounds(81, 7, 83, 21);
		contentPane.add(URLField);
		URLField.setColumns(10);

		JLabel label = new JLabel(":");
		label.setBounds(176, 10, 4, 15);
		contentPane.add(label);

		portField = new JTextField();
		portField.setBounds(184, 7, 43, 21);
		contentPane.add(portField);
		portField.setColumns(10);

		lblUser = new JLabel("User");
		lblUser.setBounds(12, 35, 57, 15);
		contentPane.add(lblUser);

		lblPassword = new JLabel("Password");
		lblPassword.setBounds(12, 60, 57, 15);
		contentPane.add(lblPassword);

		userField = new JTextField();
		userField.setBounds(81, 32, 146, 21);
		contentPane.add(userField);
		userField.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(81, 57, 146, 21);
		contentPane.add(passwordField);

		JButton GenerateBtn = new JButton("Generate");
		GenerateBtn.setBounds(551, 5, 99, 25);
		GenerateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection conn = null;
				try {
					String DBType = list.getSelectedValue().toString();
					String User = userField.getText();
					char[] Passwordc = passwordField.getPassword();
					String Password = String.valueOf(Passwordc);					
					String host = URLField.getText();
					String DBName = DBNamefield.getText();
					int port = Integer.parseInt(portField.getText());
					conn = ConnectionFactory.getConnection(DBType, host, DBName, port, User, Password);
					if (conn == null) {
						JOptionPane.showMessageDialog(null, "연결 실패! 유저이름 또는 비밀번호를 확인해주세요.", "Connection Test Failed!",
								JOptionPane.ERROR_MESSAGE);
						return;
					} else {
						Statement stmt = conn.createStatement();
						String tn = TableName.getText();
						String sql = null;
						switch(DBType)
						{
						case "Oracle":
							sql = "CREATE TABLE "+tn+" (";
						case "MySQL":
							sql = "CREATE TABLE "+DBName+"."+tn+" (";
						case "MariaDB":
						
						}
						
						
						
						
						
						
						for (int i = 0; i < table.getRowCount(); i++) {// rows
							sql = sql+"" + table.getValueAt(i, 1).toString()+"";
							sql = sql+" " + table.getValueAt(i, 2).toString()+"";
							switch (table.getValueAt(i, 2).toString()) {
							case "Int":
							case "Number":
							case "Float":
							case "DATE":
								break;
							default:
								sql = sql +"("+ table.getValueAt(i, 3).toString()+")";
								break;
							}
							if((Boolean)table.getValueAt(i, 4) == true)
							{
								sql = sql+" NOT NULL";
							}
							
							sql = sql+",";
						}
						
						
						sql = sql+"CONSTRAINT PK_"+tn+" PRIMARY KEY (";
						for (int i = 0; i < table.getRowCount(); i++) {// rows
							
							if((Boolean)table.getValueAt(i, 0) == true)
							{
								sql = sql+table.getValueAt(i, 1).toString()+",";
							}
						}
						sql = sql.substring(0,sql.length()-1);
						sql = sql+")";
						sql = sql+")";
						stmt.execute(sql);
						JOptionPane.showMessageDialog(null, "성공적으로 생성하였습니다!",
								"Table Create Success!", JOptionPane.INFORMATION_MESSAGE);
						
						
							
					}

				} catch (NullPointerException ne) {
					ne.printStackTrace();
					JOptionPane.showMessageDialog(null, "데이터베이스를 선택하지 않았거나 빠트린 값이 있는지 확인해주세요.",
							"Connection Test Failed!", JOptionPane.ERROR_MESSAGE);
				} catch (NumberFormatException nue) {
					nue.printStackTrace();
					JOptionPane.showMessageDialog(null, "데이터베이스를 선택하지 않았거나 빠트린 값이 있는지 확인해주세요.",
							"Connection Test Failed!", JOptionPane.ERROR_MESSAGE);
				} catch (SQLSyntaxErrorException SQsyne) {
					SQsyne.getMessage();
					JOptionPane.showMessageDialog(null, SQsyne.getMessage(),
							"Connection Test Failed!", JOptionPane.ERROR_MESSAGE);
				} catch (Exception xe) {
					xe.printStackTrace();
				}
				finally {
					try{
		                //자원 해제
		                if(conn != null && !conn.isClosed())
		                    conn.close();
		            } catch(SQLException sqe){
		                sqe.printStackTrace();
		            }
				}

			}
		});
		contentPane.add(GenerateBtn);

		JButton ResetBtn = new JButton("Reset");
		ResetBtn.setBounds(239, 87, 99, 25);
		ResetBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				list.setSelectedIndex(0);
				userField.setText("");
				passwordField.setText("");
				URLField.setText("");
				DBNamefield.setText("");
				portField.setText("");
			}
		});
		contentPane.add(ResetBtn);

		list = new JList();
		list.setBounds(12, 153, 99, 169);
		list.setModel(new AbstractListModel() {
			String[] values = new String[] { "Oracle", "MySQL", "MariaDB" };

			public int getSize() {
				return values.length;
			}

			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list.setSelectedIndex(0);
		list.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		contentPane.add(list);

		JLabel lblDatabaseType = new JLabel("DataBase Type");
		lblDatabaseType.setBounds(12, 128, 110, 15);
		lblDatabaseType.setFont(new Font("굴림", Font.BOLD, 12));
		contentPane.add(lblDatabaseType);

		JLabel lblPresetImport = new JLabel("Preset Import / Export");
		lblPresetImport.setBounds(348, 0, 191, 21);
		lblPresetImport.setFont(new Font("굴림", Font.BOLD, 12));
		contentPane.add(lblPresetImport);

		JButton importButton = new JButton("Import...");
		importButton.setBounds(348, 23, 191, 27);
		importButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setDialogTitle("불러올 파일을 선택하세요.");
				FileNameExtensionFilter filter = new FileNameExtensionFilter("DBTG 프리셋 파일", "dbtg");
				chooser.setFileFilter(filter);
				int userSelection = chooser.showOpenDialog(null);
				File fileToOpen = null;
				try {
					if (userSelection == JFileChooser.APPROVE_OPTION) {
						fileToOpen = chooser.getSelectedFile();
						FileReader reader = null;
						reader = new FileReader(fileToOpen);
						BufferedReader br = new BufferedReader(reader);
						table.setModel(
								new DefaultTableModel(
							new Object[][] {
							},
							new String[] {
								"PK", "Name", "Type", "Length", "Not Null?"
							}
						) {
							Class[] columnTypes = new Class[] {
								Boolean.class, Object.class, Object.class, Object.class, Boolean.class
							};
							public Class getColumnClass(int columnIndex) {
								return columnTypes[columnIndex];
							}
						});
						DefaultTableModel model = (DefaultTableModel) table.getModel();
						Object[] lines = br.lines().toArray();
						for (int i = 7; i < lines.length; i++) {
							String[] row = lines[i].toString().split(" ");
							Object[] row2 = {Boolean.parseBoolean(row[0]),row[1],row[2],row[3],Boolean.parseBoolean(row[4])};
							
							model.addRow(row2);
						}
						list.setSelectedIndex(Integer.valueOf((String) lines[0]));
						TableName.setText(lines[1].toString());
						userField.setText(lines[2].toString());
						passwordField.setText(lines[3].toString());
						URLField.setText(lines[4].toString());
						DBNamefield.setText(lines[5].toString());
						portField.setText(lines[6].toString());
					}

				} catch (IOException ie) {
					ie.printStackTrace();
				}

			}
		});
		importButton.setToolTipText("사전 설정된 프리셋 파일을 가져옵니다.");
		contentPane.add(importButton);

		JButton exportbutton = new JButton("Export...");
		exportbutton.setBounds(348, 55, 191, 27);
		exportbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setDialogTitle("저장할 위치를 선택하세요.");
				FileNameExtensionFilter filter = new FileNameExtensionFilter("DBTG 프리셋 파일", "dbtg");
				chooser.setFileFilter(filter);
				int userSelection = chooser.showSaveDialog(null);
				File fileToSave = null;
				try {
					if (userSelection == JFileChooser.APPROVE_OPTION) {
						String filename = chooser.getSelectedFile().getAbsolutePath();
						String extyn = filename.substring(filename.length() - 5, filename.length());
						if (extyn.equals(".dbtg")) {
							fileToSave = chooser.getSelectedFile();
						} else {
							fileToSave = new File(chooser.getSelectedFile() + ".dbtg");
						}
						FileWriter writer = null;
						writer = new FileWriter(fileToSave);
						BufferedWriter bw = new BufferedWriter(writer);
						bw.write(Integer.toString(list.getSelectedIndex()));
						bw.newLine();
						bw.write(TableName.getText());
						bw.newLine();
						bw.write(userField.getText());
						bw.newLine();
						bw.write(String.valueOf(passwordField.getPassword()));
						bw.newLine();
						bw.write(URLField.getText());
						bw.newLine();
						bw.write(DBNamefield.getText());
						bw.newLine();
						bw.write(portField.getText());
						bw.newLine();
						for (int i = 0; i < table.getRowCount(); i++) {// rows
							for (int j = 0; j < table.getColumnCount(); j++) {// columns
								bw.write(table.getValueAt(i, j).toString() + " ");
							}
							bw.newLine();
						}
						bw.close();
						writer.close();
					}

				} catch (IOException ie) {
					ie.printStackTrace();
				}

			}
		});
		exportbutton.setToolTipText("입력한 사전 설정을 저장합니다.");
		contentPane.add(exportbutton);

		JLabel lblNewLabel = new JLabel("Data Layout");
		lblNewLabel.setBounds(124, 128, 136, 15);
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 12));
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("PK?");
		lblNewLabel_1.setBounds(12, 362, 57, 15);
		contentPane.add(lblNewLabel_1);

		JRadioButton yesradio = new JRadioButton("Yes");
		yesradio.setBounds(12, 383, 57, 23);
		contentPane.add(yesradio);

		JRadioButton noradio = new JRadioButton("No");
		noradio.setBounds(12, 407, 57, 23);
		noradio.setSelected(true);
		contentPane.add(noradio);

		ButtonGroup group = new ButtonGroup();
	
		
		
		group.add(yesradio);
		group.add(noradio);

		JLabel lblName = new JLabel("Name");
		lblName.setBounds(199, 363, 57, 15);
		contentPane.add(lblName);

		LengthField = new JTextField();
		LengthField.setBounds(275, 408, 116, 21);
		LengthField.setColumns(10);
		LengthField.setEnabled(false);
		contentPane.add(LengthField);

		txtName = new JTextField();
		txtName.setBounds(275, 360, 116, 21);
		contentPane.add(txtName);
		txtName.setColumns(10);

		JLabel lblDatatype = new JLabel("DataType");
		lblDatatype.setBounds(199, 389, 57, 15);
		contentPane.add(lblDatatype);

		JComboBox comboBox_type = new JComboBox();
		comboBox_type.setBounds(275, 384, 116, 23);
		comboBox_type.setModel(new DefaultComboBoxModel(new String[] {"int", "Number", "Float", "VARCHAR", "VARCHAR2", "DATE", "Password"}));
		comboBox_type.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox) e.getSource();
				String selectedItem = ((String) cb.getSelectedItem()).toString();
				switch (selectedItem) {
				case "Int":
				case "Number":
				case "Float":
					LengthField.setText("");
					LengthField.setEditable(false);
					LengthField.setEnabled(false);
					break;
				default:
					LengthField.setText("");
					LengthField.setEditable(true);
					LengthField.setEnabled(true);
				}
			}

		});

		contentPane.add(comboBox_type);

		JLabel lblLength = new JLabel("Length");
		lblLength.setBounds(199, 411, 57, 15);
		contentPane.add(lblLength);

		JLabel lblNewLabel_2 = new JLabel("New Column");
		lblNewLabel_2.setBounds(12, 337, 152, 15);
		lblNewLabel_2.setFont(new Font("굴림", Font.BOLD, 12));
		contentPane.add(lblNewLabel_2);

		ButtonGroup group2 = new ButtonGroup();

		JRadioButton yesradio2 = new JRadioButton("Yes");
		yesradio2.setBounds(77, 383, 57, 23);
		contentPane.add(yesradio2);

		JRadioButton noradio2 = new JRadioButton("No");
		noradio2.setSelected(true);
		noradio2.setBounds(77, 407, 57, 23);
		contentPane.add(noradio2);

		group2.add(yesradio2);
		group2.add(noradio2);

		JLabel lblNull = new JLabel("NOT NULL?");
		lblNull.setBounds(81, 362, 99, 15);
		contentPane.add(lblNull);

		JButton btnNewButton = new JButton("Add");
		btnNewButton.setBounds(416, 358, 99, 25);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Enumeration<AbstractButton> enums = group.getElements();
				boolean PK = false;
				while (enums.hasMoreElements()) { // hasMoreElements() Enum에 더 꺼낼 개체가 있는지 체크한다. 없으며 false 반환
					AbstractButton ab = enums.nextElement(); // 제네릭스가 AbstractButton 이니까 당연히 AbstractButton으로 받아야함
					JRadioButton jb = (JRadioButton) ab; // 형변환. 물론 윗줄과 이줄을 합쳐서 바로 형변환 해서 받아도 된다.

					if (jb.isSelected() && jb.getText().equals("Yes")) { // 받아낸 라디오버튼의 체크 상태를 확인한다. 체크되었을경우 true 반환.
						PK = true;
					} else if (jb.isSelected() && jb.getText().equals("No")) {
						PK = false;
					}
				}
				DefaultTableModel tModel = (DefaultTableModel) table.getModel();
				String colName = txtName.getText();
				String colType = comboBox_type.getSelectedItem().toString();
				int colLength = 0;
				try {
					if (!LengthField.getText().equals("")) {
						colLength = Integer.parseInt(LengthField.getText());
					}
				} catch (Exception xe) {
					colLength = 0;
				}
				Enumeration<AbstractButton> enums2 = group2.getElements();
				boolean NOTNULL = false;
				while (enums2.hasMoreElements()) { // hasMoreElements() Enum에 더 꺼낼 개체가 있는지 체크한다. 없으며 false 반환
					AbstractButton ab = enums2.nextElement(); // 제네릭스가 AbstractButton 이니까 당연히 AbstractButton으로 받아야함
					JRadioButton jb = (JRadioButton) ab; // 형변환. 물론 윗줄과 이줄을 합쳐서 바로 형변환 해서 받아도 된다.

					if (jb.isSelected() && jb.getText().equals("Yes")) { // 받아낸 라디오버튼의 체크 상태를 확인한다. 체크되었을경우 true 반환.
						NOTNULL = true;
					} else if (jb.isSelected() && jb.getText().equals("No")) {
						NOTNULL = false;
					}
				}
				Object[] row = new Object[] { PK, colName, colType, colLength ,NOTNULL};
				tModel.addRow(row);

			}
		});
		contentPane.add(btnNewButton);

		JButton btnReset = new JButton("Reset");
		btnReset.setBounds(416, 395, 99, 25);
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				yesradio.setSelected(false);
				noradio.setSelected(true);
				txtName.setText("");
				comboBox_type.setSelectedIndex(0);
				LengthField.setText("");
				LengthField.setEnabled(false);
			}
		});
		contentPane.add(btnReset);

		JButton btnTest = new JButton("Conn..\r\nTest");
		btnTest.setBounds(239, 7, 97, 70);
		btnTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String DBType = list.getSelectedValue().toString();
					String User = userField.getText();
					char[] Passwordc = passwordField.getPassword();
					String Password = String.valueOf(Passwordc);
					String host = URLField.getText();
					String DBName = DBNamefield.getText();
					int port = Integer.parseInt(portField.getText());
					Connection conn = ConnectionFactory.getConnection(DBType, host, DBName, port, User, Password);
					if (conn == null) {
						JOptionPane.showMessageDialog(null, "연결 실패! 유저이름 또는 비밀번호를 확인해주세요.", "Connection Test Failed!",
								JOptionPane.ERROR_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "연결 성공!", "Connection Test Success!",
								JOptionPane.INFORMATION_MESSAGE);
					}
				} catch (NullPointerException ne) {
					ne.printStackTrace();
					JOptionPane.showMessageDialog(null, "데이터베이스를 선택하지 않았거나 빠트린 값이 있는지 확인해주세요.",
							"Connection Test Failed!", JOptionPane.ERROR_MESSAGE);
				} catch (NumberFormatException nue) {
					nue.printStackTrace();
					JOptionPane.showMessageDialog(null, "데이터베이스를 선택하지 않았거나 빠트린 값이 있는지 확인해주세요.",
							"Connection Test Failed!", JOptionPane.ERROR_MESSAGE);
				} catch (Exception xe) {
					xe.printStackTrace();
				}

			}
		});
		contentPane.add(btnTest);

		JButton button = new JButton("Reset");
		button.setBounds(551, 252, 99, 70);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.setModel(
						new DefaultTableModel(
					new Object[][] {
					},
					new String[] {
						"PK", "Name", "Type", "Length", "Not Null?"
					}
				) {
					Class[] columnTypes = new Class[] {
						Boolean.class, Object.class, Object.class, Object.class, Boolean.class
					};
					public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
				});
			}
		});
		contentPane.add(button);

		JLabel lblDbname = new JLabel("DBName");
		lblDbname.setBounds(12, 91, 57, 15);
		contentPane.add(lblDbname);

		DBNamefield = new JTextField();
		DBNamefield.setBounds(81, 88, 146, 21);
		contentPane.add(DBNamefield);
		DBNamefield.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(123, 153, 407, 175);
		contentPane.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(
				new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"PK", "Name", "Type", "Length", "Not Null?"
			}
		) {
			Class[] columnTypes = new Class[] {
				Boolean.class, Object.class, Object.class, Object.class, Boolean.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));

		JButton Deletebutton2 = new JButton("delete");
		Deletebutton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int rownum = (int) spinner.getValue();
					((DefaultTableModel) table.getModel()).removeRow(rownum - 1);
				} catch (ArrayIndexOutOfBoundsException ae) {
					JOptionPane.showMessageDialog(null, "입력하신 위치의 행이 존재하는지 확인해주세요.", "행 삭제 실패!",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		Deletebutton2.setBounds(551, 217, 99, 25);
		contentPane.add(Deletebutton2);

		spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		spinner.setBounds(551, 169, 99, 38);
		contentPane.add(spinner);

		JLabel lblRowNumber = new JLabel("Row Number");
		lblRowNumber.setHorizontalAlignment(SwingConstants.CENTER);
		lblRowNumber.setVerticalAlignment(SwingConstants.BOTTOM);
		lblRowNumber.setBounds(555, 144, 95, 15);
		contentPane.add(lblRowNumber);
		
		TableName = new JTextField();
		TableName.setBounds(414, 125, 116, 21);
		contentPane.add(TableName);
		TableName.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Table Name");
		lblNewLabel_3.setFont(new Font("굴림", Font.BOLD, 12));
		lblNewLabel_3.setBounds(319, 128, 83, 15);
		contentPane.add(lblNewLabel_3);

	}
}
