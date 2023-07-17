package com.tw.aiw.front;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import org.apache.poi.ss.usermodel.CellType;
//import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.tw.aiw.model.RoleModel;


//import com.datahub.controls.ServiceControls;
//import com.datahub.front.ConsoleLog;
//import com.datahub.front.HubBuilderFrame;
//import com.datahub.front.LogInPage;
//import com.datahub.controls.ServiceControls;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JSplitPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class RoleFrame extends JFrame implements ActionListener{
	//public static Logger logger = Logger.getLogger(RoleFrame.class);
	
	private JPanel contentPane;
	
	private JTable roleTable;	
	private JTree roleTree;
	private DefaultMutableTreeNode m_rootNode ;
	private DefaultTreeModel role_model = new DefaultTreeModel(new DefaultMutableTreeNode("Root"));

	List<RoleModel> roleList;
	private String[] topNodes = {"","THX-DIG"};
	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RoleFrame frame = new RoleFrame();
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
	public RoleFrame() {
		
		this.roleList = new ArrayList();
		
		setTitle("AIW Role");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1207, 717);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Switch");
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenu mnNewMenu_1 = new JMenu("View");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Show Console");
		mnNewMenu_1.add(mntmNewMenuItem);
		mntmNewMenuItem.addActionListener(this);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_BasePanel = new JPanel();
		contentPane.add(panel_BasePanel, BorderLayout.CENTER);
		panel_BasePanel.setLayout(new BorderLayout(0, 0));
		
		JSplitPane splitPane = new JSplitPane();

		splitPane.setOneTouchExpandable(true);
		splitPane.setDividerLocation(440);
		panel_BasePanel.add(splitPane);
		
		JScrollPane scrollPane_roleTree = new JScrollPane();
		splitPane.setLeftComponent(scrollPane_roleTree);
		
		this.roleTree = new JTree(this.role_model);
		roleTree.setFont(new Font("Tahoma", Font.PLAIN, 16));
		scrollPane_roleTree.setViewportView(this.roleTree);
		//add mouse listener to tree
		MouseListener ml = new MouseAdapter() {
			  public void mousePressed(MouseEvent e) {
			    int selRow = roleTree.getRowForLocation(e.getX(), e.getY());
			    TreePath selPath = roleTree.getPathForLocation(e.getX(), e.getY());
			    if(selRow != -1) {
			      /*if(e.getClickCount() == 1) {
			        //mySingleClick(selRow, selPath);
			    	  System.out.println("xxx 1");
			      }
			      else*/ if(e.getClickCount() == 2) {
			       // myDoubleClick(selRow, selPath);
			    	  System.out.println("xxx 2");
			      }else if(SwingUtilities.isRightMouseButton(e)==true) {
						//JOptionPane.showMessageDialog(null, wmsTable.getValueAt(row, column));
			    	  System.out.println("xxx right");
					}
			    }
			  }
			};
			
			roleTree.addMouseListener(ml);
	  //		
		JScrollPane scrollPane_roleTable = new JScrollPane();
		splitPane.setRightComponent(scrollPane_roleTable);
		
		roleTable = new JTable();
		roleTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		roleTable.setFont(new Font("Dialog", Font.PLAIN, 10));
		roleTable.setCellSelectionEnabled(true);
		scrollPane_roleTable.setViewportView(roleTable);
		roleTable.setGridColor(Color.LIGHT_GRAY);
		int x = roleTable.getRowHeight();
		roleTable.setRowHeight(x+5);
		
		roleTable.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent me) {
				int row = roleTable.getSelectedRow();
				int column = roleTable.getSelectedColumn();
				if(me.getClickCount() == 2) {
					//int row = wmsTable.getSelectedRow();
					//int column = wmsTable.getSelectedColumn();
					JOptionPane.showMessageDialog(null, roleTable.getValueAt(row, column));
				}else if(SwingUtilities.isRightMouseButton(me)==true) {
					JOptionPane.showMessageDialog(null, roleTable.getValueAt(row, column));
				}
			}
					
		});//end of mouseListener
		
		JPanel panel_upPanel = new JPanel();
		contentPane.add(panel_upPanel, BorderLayout.NORTH);
		panel_upPanel.setPreferredSize(new Dimension(1207,130));
		panel_upPanel.setLayout(null);
		
		JComboBox comboBox = new JComboBox(this.topNodes);
		comboBox.setBounds(338, 12, 119, 22);
		panel_upPanel.add(comboBox);
		
		JLabel lbl_topNode = new JLabel("Root Node");
		lbl_topNode.setBounds(248, 11, 78, 22);
		panel_upPanel.add(lbl_topNode);
		
		JButton btn_loadBtn = new JButton("Load");
		btn_loadBtn.setBounds(469, 11, 89, 23);
		btn_loadBtn.addActionListener(this);
		panel_upPanel.add(btn_loadBtn);
		
		JPanel panel_bottomPanel = new JPanel();
		contentPane.add(panel_bottomPanel, BorderLayout.SOUTH);

	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		 if (e.getActionCommand().equalsIgnoreCase("Show Console")){
	        //
			
	      }else if(e.getActionCommand().equalsIgnoreCase("Load")){
	    	  try {
	    		  this.loadRoleList();
	    	  }catch(Exception ex) {
	    		  ex.printStackTrace();
	    	  }
	    	
	      }
		
	}
	
	private void loadRoleList() throws IOException {
		if(this.roleList != null)
			this.roleList.clear();
		//Create a file chooser
				final JFileChooser fc = new JFileChooser();
			
				//In response to a button click:
				int returnVal = fc.showOpenDialog(RoleFrame.this);
				 
		        if (returnVal == JFileChooser.APPROVE_OPTION) {
		            File file = fc.getSelectedFile();
		            //This is where a real application would open the file.
		            System.out.println("Opening: " + file.getPath()+file.getName() + "." );

		            // Create a FileInputStream object to read the Excel file
		            FileInputStream inputStream = new FileInputStream(file);
		            // Create a Workbook object for the Excel file
		            //Workbook workbook = WorkbookFactory.create(inputStream);
		            
		            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);

		            // Get first/desired sheet from the workbook
		           // XSSFSheet sheet = workbook.getSheetAt(1);
		            XSSFSheet sheet = workbook.getSheet("Roles");

		            // Get the sheet containing the table
		            //Sheet sheet = workbook.getSheet("Sheet1");
		            //Sheet sheet = workbook.getSheetAt(0);
		            if(sheet !=null) { 

			            // Get the rows of the table
			            int startRow = sheet.getFirstRowNum() ;// skip the header row
			            int endRow = sheet.getLastRowNum();
			            System.out.println("start:"+startRow+"end:"+endRow);
			            // Get the columns of the table
			           // int startCol = 0;
			           // int endCol = 2;
			            
			            XSSFRow row;
			            XSSFCell cell;
			            Iterator rows = sheet.rowIterator();
			            int countRow = 1;
			            while(rows.hasNext()) {
			            	
			            	row = (XSSFRow) rows.next();
			             if(countRow>0) {
			            	Iterator cells = row.cellIterator();
			            	//System.out.print("Row:" +countRow+"->");
		            		RoleModel roleModel = new RoleModel();
			            	int countCol = 0;
			            	while(cells.hasNext()) {
			            		cell = (XSSFCell) cells.next();
			            		if(countCol ==3 || countCol ==4 || countCol ==5 || countCol ==17 || countCol ==21 || countCol ==30) {
			            			
			            		  this.setRoleModelByCol(countCol, roleModel, cell);
			            		}
			            		
			            		
			            		countCol ++;
			            	}
			            	System.out.println("\n");
				             this.roleList.add(roleModel);
			            	countRow ++;
			              }//if after while
			            }//outter while
			           // System.out.println(this.roleList.size());
			            RoleModel test = this.roleList.get(161);
			            System.out.println(this.roleList.size());
			            System.out.println(test.getEntityName()+" | "+test.getEntityNumber()+"  | "+test.getEntityParent()+"  |  "+ test.getPhysicalAssetStatus()+
			            		"   |  "+ test.getInProjectScope()+ "   |  "+  test.getPlannedChangesToRole());
		            	
		            }else{
		            	JOptionPane.showMessageDialog(this, "Can't find sheet Assets.",
		                        "error.", JOptionPane.ERROR_MESSAGE);
		            	}
		            
		            
		            
		        } else {
		        	 //System.out.println("Open command cancelled by user." );

		 			JOptionPane.showMessageDialog(this, "Open command cancelled by user.",
		                 "Info.", JOptionPane.INFORMATION_MESSAGE);
		        }
					
		
		
	}
	
	private void setRoleModelByCol(int countCol, RoleModel roleModel, XSSFCell cell) {
		String holder = "";
				
		if(cell.getCellType() ==CellType.STRING) {
 		  // System.out.print("Cell String "+countCol+":"+cell.getStringCellValue());
 		   holder = cell.getStringCellValue();
		}else if(cell.getCellType() ==CellType.NUMERIC) {
     		   holder = String.valueOf(cell.getNumericCellValue());
     		//   System.out.print("Cell num "+countCol+":"+holder);
		}else if(cell.getCellType() ==CellType.BLANK) {
     		//   System.out.print("Cell blank "+countCol+":"+ holder);
     		   holder ="";
		}else if(cell.getCellType() ==CellType.BOOLEAN) {
			holder = String.valueOf(cell.getBooleanCellValue());
 		  // System.out.print("Cell boolean "+countCol+":"+ holder);
		}
		
		if(countCol == 3) {
			roleModel.setEntityNumber(holder);
		}else if(countCol == 4) {
			roleModel.setEntityName(holder);
		}else if(countCol == 5) {
			roleModel.setInProjectScope(holder);
		}else if(countCol == 17) {
			roleModel.setPhysicalAssetStatus(holder);
		}else if(countCol == 21) {
			roleModel.setPlannedChangesToRole(holder);
		}else if(countCol == 30) {
			roleModel.setEntityParent(holder);
		}
	}

}
