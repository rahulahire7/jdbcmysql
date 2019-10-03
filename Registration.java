import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class Registration implements ActionListener
{
	int i;

	Connection con;
	PreparedStatement ps;

	JFrame f1;
	JPanel p1;
	JLabel lrno,lfnm;
	JTextField trno,tfnm;
	JButton bsave,bdelete;
	public Registration()
	{
		f1=new JFrame("Registration Page");
		f1.setSize(400,400);
		f1.setVisible(true);

		p1=new JPanel();
		f1.add(p1);
		p1.setBackground(Color.white);

		GridLayout gl=new GridLayout(3,2);
		p1.setLayout(gl);
			
		lrno=new JLabel("Reg No");
		trno=new JTextField(10);

		lfnm=new JLabel("First Name");
		tfnm=new JTextField(10);

		bsave=new JButton("Save");
		bdelete=new JButton("Delete");
		p1.add(lrno);
		p1.add(trno);
		p1.add(lfnm);
		p1.add(tfnm);
		p1.add(bsave);
		p1.add(bdelete);
		bsave.addActionListener(this);
		bdelete.addActionListener(this);
		
		

	}
	public void getConnection()
	{
		try{
		Class.forName("com.mysql.jdbc.Driver");
            	System.out.println("Hello...");
            	con=DriverManager.getConnection("jdbc:mysql://localhost:3306/maharashtra_airline","root","trupti");   
            	}
		catch(Exception e)
		{
		System.out.println(e);
		}
	}
	public void actionPerformed(ActionEvent e)
	{
		getConnection();
			if(e.getSource()==bsave)
			{
				String s1=trno.getText();
				int rno=Integer.parseInt(s1);
				String s2=tfnm.getText();
				System.out.println("Hello.."+s1);		
				try
				{
     					System.out.println("Welcome.."+con);
					ps=con.prepareStatement("insert into test values(?,?)");
					ps.setInt(1,rno);
					ps.setString(2,s2);
					i=ps.executeUpdate();//DML
					if(i>0)
					{
						System.out.println("Record Save");
					}
				}
				catch(Exception e1)
				{
					System.out.println(e1);
				}
			}
			if(e.getSource()==bdelete)
			{
				try{
				int r=Integer.parseInt(trno.getText());
				ps=con.prepareStatement("delete from test where rno=?");
				ps.setInt(1,r);
				i=ps.executeUpdate();	
				if(i>0)
				{
				System.out.println("Record Deleted..");
				}
				}
				catch(Exception e1)
				{
					System.out.println(e1);
				}	
			}
		}	
	}
