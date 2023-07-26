package com.tw.aiw.controls;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.tw.aiw.front.RoleFrame;
import com.tw.aiw.model.RoleEntityModel;

public class RoleEntityService {
	
	public List<RoleEntityModel> roleList;
	RoleFrame roleFrame;
	//provide two Hash tables, one for tree node view builder, another for retrieving by key
	public Hashtable roleTableHashtable,roleTreeHashTable;
	private Vector parentsList, childrenList;
	
	
	public RoleEntityService(RoleFrame rf) {
		this.roleFrame = rf;
		this.roleList = new ArrayList();
		this.roleTreeHashTable = new Hashtable<String, Vector>();
		this.parentsList = new Vector();
	    this.childrenList = new Vector();
	}
	
	public void loadRoleList() throws IOException{
		 File file = new File("aBook1.xlsx");
         //This is where a real application would open the file.
         System.out.println("Opening: " + file.getPath() );

         // Create a FileInputStream object to read the Excel file
         FileInputStream inputStream = new FileInputStream(file);
         // Create a Workbook object for the Excel file
         //Workbook workbook = WorkbookFactory.create(inputStream);
         
         XSSFWorkbook workbook = new XSSFWorkbook(inputStream);

         // Get first/desired sheet from the workbook
 
         XSSFSheet sheet = workbook.getSheet("Roles");

         // Get the sheet containing the table
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
         		RoleEntityModel roleModel = new RoleEntityModel();
	            	int countCol = 0;
	            	while(cells.hasNext()) {
	            		cell = (XSSFCell) cells.next();
	            		if(countCol ==3 || countCol ==4 || countCol ==5 || countCol ==17 || countCol ==21 || countCol ==30) {
	            			
	            		  this.setRoleModelByCol(countCol, roleModel, cell);
	            		}
	            		
	            		
	            		countCol ++;
	            	}
	            	//System.out.println("\n");
		             this.roleList.add(roleModel);
	            	countRow ++;
	              }//if after while
	            }//outter while
	           // System.out.println(this.roleList.size());
	            RoleEntityModel test = this.roleList.get(161);
	            System.out.println(this.roleList.size());
	            System.out.println(test.getEntityName()+" | "+test.getEntityNumber()+"  | "+test.getEntityParent()+"  |  "+ test.getPhysicalAssetStatus()+
	            		"   |  "+ test.getInProjectScope()+ "   |  "+  test.getPlannedChangesToRole());
         	
         }else{
         	JOptionPane.showMessageDialog(this.roleFrame, "Can't find sheet Assets.",
                     "error.", JOptionPane.ERROR_MESSAGE);
         	}
         
         
	}

	public void buildRoleTreeHastable() {
		RoleEntityModel holder;
		for(int i=0; i<this.roleList.size(); i++) {
			holder = (RoleEntityModel) this.roleList.get(i);
			if(this.roleTreeHashTable.containsKey((String)holder.getEntityParent())) {
				//System.out.println("Key exist:"+holder.getEntityParent());
				Vector x = (Vector)this.roleTreeHashTable.get(holder.getEntityParent());
				if(!x.contains(holder.getEntityNumber()))//avoid dup
					x.add(holder.getEntityNumber());
			}else {
			//	System.out.println("New Key found:"+holder.getEntityParent());
				Vector v = new Vector<String>();
				v.add(holder.getEntityNumber());//add child
				this.roleTreeHashTable.put(holder.getEntityParent(), v);
			}//else
		}//for
		/*
		 *test 
		System.out.println("roleTreeHashtble size:"+this.roleTreeHashTable.size());
		Vector z = (Vector)this.roleTreeHashTable.get("Root");
		if(z!=null && z.size()>0) {
		for(int s=0; s<z.size();s++)
			System.out.println(z.get(s));
		}else System.out.println("root doesn't; exist");*/
		
	}
	
	public void buildUniqueParentAndChildrentList() {
		RoleEntityModel holder;
		this.parentsList.clear();
		for(int i=0; i<this.roleList.size(); i++) {
			holder = (RoleEntityModel) roleList.get(i);
			if(!this.parentsList.contains(holder.getEntityParent()))
				this.parentsList.add(holder.getEntityParent());
			if(!this.childrenList.contains(holder.getEntityNumber()))
				this.childrenList.add(holder.getEntityNumber());
		}
		System.out.println("unique parents:"+this.parentsList.size());
		System.out.println("unique children:"+this.childrenList.size());
		/*
		 * test*/
		  String x;
		for(int t=0; t<this.parentsList.size(); t++) {
		 if(!this.childrenList.contains((String)this.parentsList.get(t)))
			 System.out.println("Parent that has no child:"+this.parentsList.get(t));
		}
	}

	public Vector getRoleTableHeaderColumnNames() {
		Vector columnNames = new Vector<String>();
		columnNames.add("Entity Number");
		columnNames.add("Entity Name");
		columnNames.add("Physical Asset Status");
		columnNames.add("Planned Changes T oRole");
		columnNames.add("In Project Scope");
		columnNames.add("Entity Parent");
		
		return columnNames;
	}
	private void setRoleModelByCol(int countCol, RoleEntityModel roleModel, XSSFCell cell) {
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
			if(holder.equals(""))
				roleModel.setEntityParent("Root");
			else
			roleModel.setEntityParent(holder);
		}
	}
	
	
	@Deprecated
	public void loadRoleList1() throws IOException {
		if(this.roleList != null)
			this.roleList.clear();
		//Create a file chooser
				final JFileChooser fc = new JFileChooser();
			
				//In response to a button click:
				int returnVal = fc.showOpenDialog(roleFrame);
				 
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
		            		RoleEntityModel roleModel = new RoleEntityModel();
			            	int countCol = 0;
			            	while(cells.hasNext()) {
			            		cell = (XSSFCell) cells.next();
			            		if(countCol ==3 || countCol ==4 || countCol ==5 || countCol ==17 || countCol ==21 || countCol ==30) {
			            			
			            		  this.setRoleModelByCol(countCol, roleModel, cell);
			            		}
			            		
			            		
			            		countCol ++;
			            	}
			            	//System.out.println("\n");
				             this.roleList.add(roleModel);
			            	countRow ++;
			              }//if after while
			            }//outter while
			           // System.out.println(this.roleList.size());
			            RoleEntityModel test = this.roleList.get(161);
			            System.out.println(this.roleList.size());
			            System.out.println(test.getEntityName()+" | "+test.getEntityNumber()+"  | "+test.getEntityParent()+"  |  "+ test.getPhysicalAssetStatus()+
			            		"   |  "+ test.getInProjectScope()+ "   |  "+  test.getPlannedChangesToRole());
		            	
		            }else{
		            	JOptionPane.showMessageDialog(this.roleFrame, "Can't find sheet Assets.",
		                        "error.", JOptionPane.ERROR_MESSAGE);
		            	}
		            
		            
		            
		        } else {
		        	 //System.out.println("Open command cancelled by user." );

		 			JOptionPane.showMessageDialog(this.roleFrame, "Open command cancelled by user.",
		                 "Info.", JOptionPane.INFORMATION_MESSAGE);
		        }
					
		
		
	}

}
