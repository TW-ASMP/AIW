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
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
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

import com.tw.aiw.controls.RoleEntityService;
import com.tw.aiw.model.RoleEntityModel;


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
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class RoleFrame extends JFrame implements ActionListener{
	//public static Logger logger = Logger.getLogger(RoleFrame.class);
	
	private JPanel contentPane;
	
	private JTable roleTable;	
	private DefaultTableModel roleTblModel;
	private TableRowSorter sorter;
	
	private JTextField searchBox;
	
	private JTree roleTree;
	private DefaultMutableTreeNode m_rootNode ;
	private DefaultTreeModel role_model = new DefaultTreeModel(new DefaultMutableTreeNode("Root"));

	public JPopupMenu roleTreePopupMenu, roleTablePopupMenu;
	
	private RoleEntityService roleEntityService;
	List<RoleEntityModel> roleList;
	private String[] topNodes = {"","THX-DIG"};
	private String[] columnIndex = {"0","1","2","3","4","5"};
	private JTextField filterTextField;
	private JTextField filterTextField1;
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
		
		this.roleEntityService = new RoleEntityService(this);
		//this.roleList = new ArrayList();
		
		setTitle("AIW Role");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1207, 717);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Load");
		mnNewMenu.add(mntmNewMenuItem_1);
		mntmNewMenuItem_1.addActionListener(this);
		
		JMenu mnNewMenu_1 = new JMenu("View");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Show Console");
		mnNewMenu_1.add(mntmNewMenuItem);
		mntmNewMenuItem.addActionListener(this);
		
		JMenuItem mntmNewMenuItem1 = new JMenuItem("SortByWMSTree");
		mnNewMenu_1.add(mntmNewMenuItem1);
		mntmNewMenuItem1.addActionListener(this);
		
		JMenuItem mntmNewMenuItem2 = new JMenuItem("SortByLine");
		mnNewMenu_1.add(mntmNewMenuItem2);
		mntmNewMenuItem2.addActionListener(this);
		
		
		
		
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
		//initialize popup menu of tree
		 this.roleTreePopupMenu = new JPopupMenu("Tree Actions");
		  JMenuItem roleTreeMI1 = new JMenuItem("Find in table");
		  JMenuItem roleTreeMI2 = new JMenuItem("Add Child");
		  JMenuItem roleTreeMI3 = new JMenuItem("Set as Root");
		  roleTreeMI1.addActionListener(this);
		  roleTreeMI2.addActionListener(this);
		  roleTreeMI3.addActionListener(this);
		  roleTreePopupMenu.add(roleTreeMI1);  
		  roleTreePopupMenu.add(roleTreeMI2);  
		  roleTreePopupMenu.add(roleTreeMI3); 
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
			    	  showPopup(e);
					}
			    }
			  }

			   void showPopup(MouseEvent me) {
				      if(me.isPopupTrigger())
				    	 roleTreePopupMenu.show(me.getComponent(), me.getX(), me.getY());
				   }
			};
			
			roleTree.addMouseListener(ml);
	  //		
		JScrollPane scrollPane_roleTable = new JScrollPane();
		splitPane.setRightComponent(scrollPane_roleTable);
		
	    //table
		roleTable = new JTable();
		roleTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		roleTable.setFont(new Font("Dialog", Font.PLAIN, 10));
		roleTable.setCellSelectionEnabled(true);
		roleTable.setAutoCreateRowSorter(true);
		scrollPane_roleTable.setViewportView(roleTable);
		roleTable.setGridColor(Color.LIGHT_GRAY);
		int x = roleTable.getRowHeight();
		roleTable.setRowHeight(x+5);

		//initialize popup menu of main table
		this.roleTablePopupMenu  = new JPopupMenu("Table Actions");
		JMenuItem mainTblM1 = new JMenuItem("find in Tree");
		JMenuItem mainTblM2 = new JMenuItem("action t2");
		JMenuItem mainTblM3 = new JMenuItem("action t3");
		mainTblM1.addActionListener(this);
		mainTblM2.addActionListener(this);
		mainTblM3.addActionListener(this);
		roleTablePopupMenu.add(mainTblM1);
		roleTablePopupMenu.add(mainTblM2);
		roleTablePopupMenu.add(mainTblM3);
		
		//Roletable popup menu Lisetner
		roleTable.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent me) {
				int row = roleTable.getSelectedRow();
				int column = roleTable.getSelectedColumn();
				if(me.getClickCount() == 2) {
					//int row = wmsTable.getSelectedRow();
					//int column = wmsTable.getSelectedColumn();
					JOptionPane.showMessageDialog(null, roleTable.getValueAt(row, column));
				}else if(SwingUtilities.isRightMouseButton(me)==true) {
					//JOptionPane.showMessageDialog(null, roleTable.getValueAt(row, column));
					showPopup(me);
				}
			}

			void showPopup(MouseEvent m) {
				int row = roleTable.getSelectedRow();
				int column = roleTable.getSelectedColumn();
			 //if(m.isPopupTrigger())
				   roleTablePopupMenu.show(m.getComponent(), m.getX(), m.getY());
				}
					
		});//end of mouseListener
		
		this.roleTblModel = new DefaultTableModel(this.roleEntityService.getRoleTableHeaderColumnNames(),0){

		    @Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
		};
		this.roleTable.setModel(this.roleTblModel);
		
		JPanel panel_upPanel = new JPanel();
		contentPane.add(panel_upPanel, BorderLayout.NORTH);
		panel_upPanel.setPreferredSize(new Dimension(1207,130));
		panel_upPanel.setLayout(null);

		/*		
		JLabel lbl_topNode = new JLabel("Root Node");
		lbl_topNode.setBounds(248, 11, 78, 22);
		panel_upPanel.add(lbl_topNode);
		JComboBox comboBox = new JComboBox(this.topNodes);
		comboBox.setBounds(338, 12, 119, 22);
		panel_upPanel.add(comboBox);
		*/
		
		searchBox = new JTextField();
		searchBox.setBounds(90, 100, 165, 22);
		panel_upPanel.add(searchBox);
		JLabel lbl_searchNode = new JLabel("Search Node");
		lbl_searchNode.setBounds(10, 100, 78, 22);
		panel_upPanel.add(lbl_searchNode);
		
		JButton btn_loadBtn = new JButton("Search");
		btn_loadBtn.setBounds(260, 100, 89, 23);
		btn_loadBtn.addActionListener(this);
		panel_upPanel.add(btn_loadBtn);
		
		// filter section
		
		JLabel lblNewLabel = new JLabel("Filters:");
		lblNewLabel.setBounds(455, 101, 56, 26);
		panel_upPanel.add(lblNewLabel);
		
		this.sorter = new TableRowSorter<>(this.roleTblModel);
		this.roleTable.setRowSorter(sorter);
		
		filterTextField = new JTextField();
		filterTextField.setBounds(499, 101, 130, 26);
		panel_upPanel.add(filterTextField);
		filterTextField.setColumns(10);
		
		JComboBox filterComboBox = new JComboBox(this.columnIndex);
		filterComboBox.setBounds(627, 101, 78, 26);
		panel_upPanel.add(filterComboBox);
		
		filterTextField1 = new JTextField();
		filterTextField1.setBounds(717, 101, 130, 26);
		panel_upPanel.add(filterTextField1);
		filterTextField1.setColumns(10);
		
		JComboBox filterComboBox1 = new JComboBox(this.columnIndex);
		filterComboBox1.setBounds(843, 101, 78, 26);
		panel_upPanel.add(filterComboBox1);
		
		DocumentListener dListener = new DocumentListener() {
			 @Override
	            public void insertUpdate(DocumentEvent e) {
	                //update(e);
				 search(filterTextField.getText(),Integer.parseInt((String)filterComboBox.getSelectedItem()));
	            }

	            @Override
	            public void removeUpdate(DocumentEvent e) {
	                //update(e);
				 search(filterTextField.getText(),Integer.parseInt((String)filterComboBox.getSelectedItem()));
	            }

	            @Override
	            public void changedUpdate(DocumentEvent e) {
	                //update(e);
					 search(filterTextField.getText(),Integer.parseInt((String)filterComboBox.getSelectedItem()));
	            }
             /*
	            private void update(DocumentEvent e) {
	                String text = filterTextField.getText();
	                if (text.trim().length() == 0) {
	                    sorter.setRowFilter(null);
	                } else {
	                   // sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text, 4));
	                    sorter.setRowFilter(RowFilter.regexFilter(text, 4));
	                }
	            }
	            */
	            public void search(String str, int column) {
	                if (str.length() == 0) {
	                   sorter.setRowFilter(null);
	                	//sorter.setRowFilter(RowFilter.regexFilter("/^$/", 3));
	                } else if (str.length()>0 && str.trim().length()==0){
	                //	System.out.println("xx"+str+"yy");
	                	sorter.setRowFilter(RowFilter.regexFilter("(^$)", column));
	                	
	                }else {
	                 //sorter.setRowFilter(RowFilter.regexFilter(str, column)); //case sensitive
	                  sorter.setRowFilter(RowFilter.regexFilter("(?i)" + str, column));
	                  
	                }
	             }
			
		};
		filterTextField.getDocument().addDocumentListener(dListener);
		
		
		
		//bottom
		JPanel panel_bottomPanel = new JPanel();
		contentPane.add(panel_bottomPanel, BorderLayout.SOUTH);

	}
	
	public RowFilter setRowFilterValue(String str, int column) {
		RowFilter filter = null; 
		if(str.length()== 0) {
			filter.regexFilter(null);
		}else if(str.length()>0 && str.trim().length()==0){
            //	System.out.println("xx"+str+"yy");
        	filter.regexFilter("(^$)", column);
        	
        }else {
         //sorter.setRowFilter(RowFilter.regexFilter(str, column)); //case sensitive
         filter.regexFilter("(?i)" + str, column);
          
        }
		
		return filter;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		 if (e.getActionCommand().equalsIgnoreCase("Show Console")){
	        //
			
	      }else if(e.getActionCommand().equalsIgnoreCase("Load")){
	    	  try {	    		
	    		  this.roleEntityService.loadRoleList();
	    		  //this.roleEntityService.buildUniqueParentAndChildrentList();
	    		  this.roleEntityService.buildRoleTreeHastable();
	    		  this.drawRoleTree();
	    		  this.drawRoleTableLineByLine();
	    	  }catch(Exception ex) {
	    		  ex.printStackTrace();
	    	  }
	    	
	      }else if(e.getActionCommand().equalsIgnoreCase("SortByWMSTree")){
	    	  this.roleTblModel.setRowCount(0);
	    	  this.drawRoleTableByTree();
	    	  
	      }else if(e.getActionCommand().equalsIgnoreCase("SortByLine")){
	    	  this.roleTblModel.setRowCount(0);
	    	  this.drawRoleTableLineByLine();
	    	  
	      }else if(e.getActionCommand().equalsIgnoreCase("find in table")){
	    	  
	    	  DefaultMutableTreeNode selNode = (DefaultMutableTreeNode) roleTree
	    	            .getLastSelectedPathComponent(); 
	    	 
	    	  //JOptionPane.showMessageDialog(null, "find "+ selNode.toString()+" in table");
	    	  this.searchAndFocusCell(selNode.toString());
	    	  
	      }else if(e.getActionCommand().equalsIgnoreCase("find in tree")){

				int row = roleTable.getSelectedRow();
				int column = roleTable.getSelectedColumn();
			   String x = roleTable.getValueAt(row,column).toString();
		    	  //JOptionPane.showMessageDialog(null, "find "+x+" in tree");
			   if(x != null && x.length()>0) {
				   searchAndFocusNode(x);
			   }	    	  
	      }else if(e.getActionCommand().equalsIgnoreCase("Search")){

				String s = this.searchBox.getText();
				if(s!=null && s.length()>0) {
					this.searchAndFocusNode(s);
					this.searchAndFocusCell(s);
				}
	      }
		
	}
	
	private void drawRoleTree() {
		this.m_rootNode = (DefaultMutableTreeNode) this.role_model.getRoot();
		this.m_rootNode.setUserObject("Root");
		this.role_model.nodeChanged(this.m_rootNode);
		//2. Recursion 
		if(this.roleEntityService.roleTreeHashTable.containsKey("Root")) {
		//logger.debug(this.serviceCtrls.wmsEntityService.getWmsTreeTable().containsKey(this.m_rootNode.toString()));
			Vector v = (Vector)this.roleEntityService.roleTreeHashTable.get("Root");
			
			if(v.size()>0) {
				this.drawRoleTreeRec(m_rootNode, v);
			}
		}else System.out.println("Can't find Root");
		
	}
	
	private void drawRoleTreeRec(DefaultMutableTreeNode parent, Vector v) {
		for(int i=0; i<v.size();i++) {
			DefaultMutableTreeNode child = new DefaultMutableTreeNode(v.get(i).toString());
			//1.draw child
			// m_model.insertNodeInto(newNode, selNode, selNode.getChildCount());
			this.role_model.insertNodeInto(child, parent, parent.getChildCount());
			//2.down to bottom
			//if(this.serviceCtrls.wmsEntityService.getWmsTreeTable().containsKey(child.toString())){
			if(this.roleEntityService.roleTreeHashTable.containsKey(child.toString())){
				this.drawRoleTreeRec(child, (Vector)this.roleEntityService.roleTreeHashTable.get(child.toString()));
			}
			
			//open
			 TreeNode[] nodes = role_model.getPathToRoot(child);
		     TreePath path = new TreePath(nodes);
		     roleTree.scrollPathToVisible(path);
		     roleTree.setSelectionPath(path);
		     roleTree.startEditingAtPath(path); 
		}
		
	}

	private void drawRoleTableLineByLine() {
		for(int i=0; i<this.roleEntityService.roleList.size(); i++) {
			this.roleTblModel.addRow(this.roleEntityService.roleList.get(i).getByColumns());
		}
		//this.mainTable.setModel(mainTblModel);
		
	}
	
	private void drawRoleTableByTree() {
		
	}
	
	private void drawRoleTableByTreeRec() {
		
	}
	//search tree and set focus
    private void searchAndFocusNode(String searchTerm) {
        DefaultMutableTreeNode node =  findNode((DefaultMutableTreeNode)this.roleTree.getModel().getRoot(), searchTerm);
        if (node != null) {
            // Select the node and scroll it into view
            TreeNode[] path = node.getPath();
            TreePath treePath = new TreePath(path);
            this.roleTree.scrollPathToVisible(treePath);
            this.roleTree.setSelectionPath(treePath);
            this.roleTree.requestFocusInWindow();
        } else {
            // If the search term is not found, show a message
            JOptionPane.showMessageDialog(this, "Node with value \"" + searchTerm + "\" not found!");
        }
    }

    private DefaultMutableTreeNode findNode(TreeNode root, String searchTerm) {
        Enumeration<TreeNode> enumeration = (Enumeration) root.children();
        while (enumeration.hasMoreElements()) {
            TreeNode node = enumeration.nextElement();
            if (node instanceof DefaultMutableTreeNode) {
                DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) node;
                Object userObject = treeNode.getUserObject();
                if (userObject != null && userObject.toString().equalsIgnoreCase(searchTerm)) {
                    return treeNode;
                } else {
                    DefaultMutableTreeNode foundNode = findNode(node, searchTerm);
                    if (foundNode != null) {
                        return foundNode;
                    }
                }
            }
        }
        return null;
    }
	
	//Search table and set focus
    private void searchAndFocusCell(String searchTerm) {
        int rowCount = this.roleTable.getRowCount();
        int colCount = this.roleTable.getColumnCount();
        boolean cellFound = false;

        // Loop through all the cells to find the search term
        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < colCount; col++) {
                Object cellValue = this.roleTable.getValueAt(row, col);
                if (cellValue != null && cellValue.toString().equalsIgnoreCase(searchTerm)) {
                    // Select the cell and bring it into view
                    this.roleTable.changeSelection(row, col, false, false);
                    this.roleTable.requestFocusInWindow();
                    cellFound = true;
                    break;
                }
            }
            if (cellFound) {
                break;
            }
        }

        // If the search term is not found, show a message
        if (!cellFound) {
            JOptionPane.showMessageDialog(this, "Cell with value \"" + searchTerm + "\" not found!");
        }
    }
}
