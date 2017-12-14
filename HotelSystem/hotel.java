package hotel;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.io.*;
import java.nio.channels.ShutdownChannelGroupException;
import java.util.regex.*;

public class hotel implements ActionListener{
	private static Connection dbTest;
	JFrame frame = new JFrame();
	JLabel title = new JLabel("호텔 관리 시스템");
	JMenuBar menu = new JMenuBar();
	JMenu file = new JMenu("File");
	JMenuItem read = new JMenuItem("Open");
	JFileChooser filechoose = new JFileChooser();
	JPanel p1 = new JPanel();
	JPanel p2 = new JPanel();
	JPanel p3 = new JPanel();
	JLabel label1 = new JLabel("투숙예약");
	JLabel label2 = new JLabel("객실 예약 현황");
	JLabel label3 = new JLabel("등록/조회");
	
	//로그인
	String username;
	String password;
	JFrame login = new JFrame();
	JLabel id = new JLabel("아이디");
    JLabel pw = new JLabel("비밀번호");
    JTextField idinput = new JTextField();
    JPasswordField pwinput = new JPasswordField();
    JButton loginbutton = new JButton("로그인");
	
	//투숙예약
	JLabel customer = new JLabel("고객명");
	JTextField customer_input = new JTextField();
	JLabel checkin = new JLabel("체크인(YYYYMMDD)");
	JTextField checkin_input = new JTextField();
	JLabel days = new JLabel("박");
	JComboBox<Integer> days_input = new JComboBox<Integer>();
	JLabel room = new JLabel("객실");
	JComboBox<Integer> room_input = new JComboBox<Integer>();
	JButton reserve = new JButton("예약 등록/변경");
	JButton cancle = new JButton("예약 취소");
	
	//객실 예약 현황
	JLabel today = new JLabel();
	JPanel table = new JPanel();
	JLabel table_cell[] = new JLabel[20];
	
	
	//등록/조회
	JTabbedPane tab = new JTabbedPane();
	JPanel tab1 = new JPanel();
	JPanel tab2 = new JPanel();
	JPanel tab3 = new JPanel();
	JLabel customer_name = new JLabel("고객명");
	JTextField customer_name_input = new JTextField();
	JButton customer_signup = new JButton("회원가입");
	JButton customer_list = new JButton("조회");
	JTextArea customer_info = new JTextArea();
	JScrollPane cust_info = new JScrollPane();
	JLabel room_num = new JLabel("객실");
	JComboBox<Integer> room_num_input = new JComboBox<Integer>();
	JTextArea room_num_info = new JTextArea();
	JScrollPane room_info = new JScrollPane();
	JLabel employee = new JLabel("직원명");
	JTextField employee_input = new JTextField();
	JButton employee_signup = new JButton("직원등록");
	JButton employee_list = new JButton("조회");
	JTextArea employee_info = new JTextArea();
	JScrollPane em_info = new JScrollPane();
	
	//회원가입
	JFrame cust_signup = new JFrame();
	JPanel signup_cu = new JPanel();
	JLabel signup_customer = new JLabel("고객명");
	JTextField signup_customer_input = new JTextField();
	JLabel signup_cu_sex = new JLabel("성별");
	JComboBox<String> signup_cu_sex_input = new JComboBox<String>();
	JLabel signup_cu_address = new JLabel("주소");
	JComboBox<String> signup_cu_address_input = new JComboBox<String>();
	JLabel signup_cu_tel = new JLabel("연락처");
	JTextField signup_cu_tel_input = new JTextField();
	JButton signup_cu_ok = new JButton("가입신청");
	JButton signup_cu_cancle = new JButton("취소");
	
	JFrame em_signup = new JFrame();
	JPanel signup_em = new JPanel();
	JLabel signup_employee = new JLabel("직원명");
	JTextField signup_employee_input = new JTextField();
	JLabel signup_em_sex = new JLabel("성별");
	JComboBox<String> signup_em_sex_input = new JComboBox<String>();
	JLabel signup_em_address = new JLabel("주소");
	JComboBox<String> signup_em_address_input = new JComboBox<String>();
	JLabel signup_em_tel = new JLabel("연락처");
	JTextField signup_em_tel_input = new JTextField();
	JButton signup_em_ok = new JButton("직원등록");
	JButton signup_em_cancle = new JButton("취소");
	
	public hotel(){
		frame.setBounds(0 , 0 , 1960 , 1080);
		frame.setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		title.setBounds(250,70,1400,80);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("굴림",Font.BOLD,40));
		title.setBorder(new LineBorder(Color.BLACK, 4));
		frame.add(title);
		frame.add(label1);
		frame.add(label2);
		frame.add(label3);
		frame.add(file);
		frame.add(menu);
		label1.setBounds(60, 150, 400, 50);
		label1.setFont(new Font("굴림",Font.BOLD,30));
		label2.setBounds(1010, 150, 400, 50);
		label2.setFont(new Font("굴림",Font.BOLD,30));
		label3.setBounds(60, 550, 400, 50);
		label3.setFont(new Font("굴림",Font.BOLD,30));
		menu.setBounds(0, 0, 1960, 30);
		menu.add(file);
		file.add(read);
		read.addActionListener(this);
		filechoose.setBounds(0, 0, 1960, 600);
		frame.add(today);
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		today.setText("("+sdf.format(d)+")");
		today.setBounds(1600, 150, 300, 50);
		today.setFont(new Font("굴림",Font.BOLD,30));
		
		//로그인
		login.add(id);
		login.add(idinput);
		login.add(pw);
		login.add(pwinput);
		login.add(loginbutton);
		id.setBounds(50, 50, 100, 50);
		idinput.setBounds(150, 50, 200, 50);
		pw.setBounds(50, 150, 100, 50);
		pwinput.setBounds(150, 150, 200, 50);
		loginbutton.setBounds(100, 250, 200, 50);
		loginbutton.addActionListener(this);
		login.setLayout(null);
		login.setVisible(true);
		login.setBounds(0, 0, 500, 400);
		
		//투숙예약
		p1.setBounds(50, 200, 850, 350);
		p1.setBorder(new LineBorder(Color.GRAY, 4));
		p1.setLayout(null);
		frame.add(p1);
		p1.add(customer);
		p1.add(customer_input);
		p1.add(checkin);
		p1.add(checkin_input);
		p1.add(days);
		p1.add(days_input);
		p1.add(room);
		p1.add(room_input);
		p1.add(reserve);
		reserve.addActionListener(this);
		p1.add(cancle);
		cancle.addActionListener(this);
		customer.setBounds(50, 10, 300, 50);
		customer_input.setBounds(400, 10, 300, 50);
		checkin.setBounds(50, 80, 300, 50);
		checkin_input.setBounds(400, 80, 300, 50);
		days.setBounds(50, 150, 300, 50);
		days_input.setBounds(400, 150, 300, 50);
		for(int i=1; i<=10; i++) days_input.addItem(i);
		room.setBounds(50, 220, 300, 50);
		room_input.setBounds(400, 220, 300, 50);
		reserve.setBounds(75, 290, 300, 50);
		cancle.setBounds(400, 290, 300, 50);
		
		//객실 예약 현황
		p2.setBounds(1000, 200, 850, 350);
		p2.setBorder(new LineBorder(Color.GRAY, 4));
		p2.setLayout(null);
		p2.add(table);
		table.setBounds(25, 25, 800, 300);
		table.setLayout(new GridLayout(4,5,10,10));
		for(int i=0; i<20; i++){
			table_cell[i] = new JLabel();
			table.add(table_cell[i]);
			table_cell[i].setBorder(new LineBorder(Color.BLACK,2));
			table_cell[i].setFont(new Font("굴림",Font.BOLD,20));
			table_cell[i].setOpaque(true);        
            table_cell[i].setBackground(Color.WHITE);
            table_cell[i].setHorizontalAlignment(JLabel.CENTER);
		}
		frame.add(p2);
		
		//등록/조회
		p3.setBounds(50, 600, 1800, 350);
		p3.setBorder(new LineBorder(Color.GRAY, 4));
		p3.setLayout(null);
		frame.add(p3);
		p3.add(tab);
		tab.setBounds(50, 25, 1700, 300);
		tab.addTab("고객", tab1);
		tab.addTab("객실", tab2);
		tab.addTab("직원", tab3);
		tab1.setLayout(null);
		tab2.setLayout(null);
		tab3.setLayout(null);
		tab1.add(customer_name);
		tab1.add(customer_name_input);
		tab1.add(customer_signup);
		customer_signup.addActionListener(this);
		tab1.add(customer_list);
		customer_list.addActionListener(this);
		tab1.add(cust_info);
		cust_info.setViewportView(customer_info);
		customer_info.setEditable(false);
		customer_name.setBounds(50, 50, 250, 50);
		customer_name_input.setBounds(350, 50, 250, 50);
		customer_signup.setBounds(50, 150, 250, 50);
		customer_list.setBounds(350, 150, 250, 50);
		cust_info.setBounds(650, 10, 1000, 250);
		tab2.add(room_num);
		tab2.add(room_num_input);
		room_num_input.addActionListener(this);
		tab2.add(room_info);
		room_info.setViewportView(room_num_info);
		room_num_info.setEditable(false);
		room_num.setBounds(50, 50, 250, 50);
		room_num_input.setBounds(350, 50, 250, 50);
		room_info.setBounds(650, 10, 1000, 250);
		tab3.add(employee);
		tab3.add(employee_input);
		tab3.add(employee_signup);
		employee_signup.addActionListener(this);
		tab3.add(employee_list);
		employee_list.addActionListener(this);
		tab3.add(em_info);
		em_info.setViewportView(employee_info);
		employee_info.setEditable(false);
		employee.setBounds(50, 50, 250, 50);
		employee_input.setBounds(350, 50, 250, 50);
		employee_signup.setBounds(50, 150, 250, 50);
		employee_list.setBounds(350, 150, 250, 50);
		em_info.setBounds(650, 10, 1000, 250);
		
		//회원가입
		cust_signup.setBounds(500, 300, 800, 500);
		cust_signup.add(signup_customer);
		cust_signup.add(signup_customer_input);
		cust_signup.add(signup_cu_sex);
		cust_signup.add(signup_cu_sex_input);
		cust_signup.add(signup_cu_address);
		cust_signup.add(signup_cu_address_input);
		cust_signup.add(signup_cu_tel);
		cust_signup.add(signup_cu_tel_input);
		cust_signup.add(signup_cu_ok);
		signup_cu_ok.addActionListener(this);
		cust_signup.add(signup_cu_cancle);
		signup_cu_cancle.addActionListener(this);
		cust_signup.setLayout(null);
		signup_customer.setBounds(50, 50, 300, 50);
		signup_customer_input.setBounds(350, 50, 300, 50);
		signup_cu_sex.setBounds(50, 125, 300, 50);
		signup_cu_sex_input.setBounds(350, 125, 300, 50);
		signup_cu_address.setBounds(50, 200, 300, 50);
		signup_cu_address_input.setBounds(350, 200, 300, 50);
		signup_cu_tel.setBounds(50, 275, 300, 50);
		signup_cu_tel_input.setBounds(350, 275, 300, 50);
		signup_cu_ok.setBounds(25, 350, 300, 50);
		signup_cu_cancle.setBounds(375, 350, 300, 50);
		signup_cu_sex_input.addItem("남");
		signup_cu_sex_input.addItem("여");
		signup_cu_address_input.addItem("서울");
		signup_cu_address_input.addItem("안산");
		signup_cu_address_input.addItem("시흥");
		signup_cu_address_input.addItem("대구");
		signup_cu_address_input.addItem("제주");
		signup_cu_address_input.addItem("인천");
		
		em_signup.setBounds(500, 300, 800, 500);
		em_signup.add(signup_employee);
		em_signup.add(signup_employee_input);
		em_signup.add(signup_em_sex);
		em_signup.add(signup_em_sex_input);
		em_signup.add(signup_em_address);
		em_signup.add(signup_em_address_input);
		em_signup.add(signup_em_tel);
		em_signup.add(signup_em_tel_input);
		em_signup.add(signup_em_ok);
		signup_em_ok.addActionListener(this);
		em_signup.add(signup_em_cancle);
		signup_em_cancle.addActionListener(this);
		em_signup.setLayout(null);
		signup_employee.setBounds(50, 50, 300, 50);
		signup_employee_input.setBounds(350, 50, 300, 50);
		signup_em_sex.setBounds(50, 125, 300, 50);
		signup_em_sex_input.setBounds(350, 125, 300, 50);
		signup_em_address.setBounds(50, 200, 300, 50);
		signup_em_address_input.setBounds(350, 200, 300, 50);
		signup_em_tel.setBounds(50, 275, 300, 50);
		signup_em_tel_input.setBounds(350, 275, 300, 50);
		signup_em_ok.setBounds(25, 350, 300, 50);
		signup_em_cancle.setBounds(375, 350, 300, 50);
		signup_em_sex_input.addItem("남");
		signup_em_sex_input.addItem("여");
		signup_em_address_input.addItem("서울");
		signup_em_address_input.addItem("안산");
		signup_em_address_input.addItem("시흥");
		signup_em_address_input.addItem("대구");
		signup_em_address_input.addItem("제주");
		signup_em_address_input.addItem("인천");
	}
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == loginbutton){ //로그인
			username = idinput.getText();
            password = new String(pwinput.getPassword());
            connectDB();
            login.setVisible(false);
		}
		else if(e.getSource() == read){ //데이터파일 읽기
        	String sqlStr;
        	PreparedStatement stmt;
        	ResultSet rs;
        	int returnvalue = filechoose.showOpenDialog(frame);
        	if(returnvalue == JFileChooser.APPROVE_OPTION){
        		File returnfile = filechoose.getSelectedFile();
        		try{
        			BufferedReader in = new BufferedReader(new FileReader(returnfile));
        			String s,str[];
        			ArrayList<String> text = new ArrayList<String>();
        			int customers=0,employees=0,rooms=0;
        			while((s=in.readLine()) != null){
        				text.add(s);
        			}
        			try{
	        			customers = Integer.parseInt(text.get(0));
	        			for(int i=1; i<1+customers; i++){
	        				str = text.get(i).split("\\s+");
	    					//불러온 텍스트에서 고객 데이터를 hotel_customer에 저장
    						sqlStr = "insert into hotel_customer values('"+str[0]+"','"+str[1]+"','"+str[2]+"','"+str[3]+"')";
    			        	stmt = dbTest.prepareStatement(sqlStr);
    			        	rs = stmt.executeQuery();
	        			}
	        			employees = Integer.parseInt(text.get(customers+1));
	        			for(int i=customers+2; i<customers+employees+2; i++){
	        				str = text.get(i).split("\\s+");
	    					//불러온 텍스트에서 직원 데이터를 hotel_employee에 저장
    						sqlStr = "insert into hotel_employee values('"+str[0]+"','"+str[1]+"','"+str[2]+"','"+str[3]+"')";
    			        	stmt = dbTest.prepareStatement(sqlStr);
    			        	rs = stmt.executeQuery();
	        			}
	        			rooms = Integer.parseInt(text.get(customers+employees+2));
	        			for(int i=customers+employees+3; i<text.size(); i++){
	        				str = text.get(i).split("\\s+");
	    					 //불러온 텍스트에서 객실 데이터를 hotel_room에 저장
    						sqlStr = "insert into hotel_room values('"+str[0]+"','"+str[1]+"','"+str[2]+"')";
    			        	stmt = dbTest.prepareStatement(sqlStr);
    			        	rs = stmt.executeQuery();
	        			}
	        			//불러온 텍스트에서 객실 데이터를 읽어 객실 테이블을 만듬
	        			sqlStr = "select roomnumber from hotel_room";
	        			stmt = dbTest.prepareStatement(sqlStr);
			        	rs = stmt.executeQuery();
			        	int i=0;
			        	while(rs.next()){
			        		table_cell[i].setText(rs.getString("roomnumber"));
			        		room_input.addItem(Integer.parseInt(rs.getString("roomnumber")));
			        		room_num_input.addItem(Integer.parseInt(rs.getString("roomnumber")));
			        		i++;
			        	}
	        			roomcheck();
        			}
        			catch(Exception ex){
        				JOptionPane.showMessageDialog(frame,"파일을 읽는데 실패했습니다.");
        			}
        		}
        		catch(IOException ie){
        			System.out.println("파일 읽기 실패");
        			JOptionPane.showMessageDialog(frame,"잘못된 파일입니다.");
        		}
        	}
        }
		else if(e.getSource() == reserve){ //예약
			String sqlStr;
			String customer_tel,employee_tel;
        	PreparedStatement stmt;
        	ResultSet rs;
        	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        	Boolean ischange=false;
        	String room_number_="",checkin_="";
        	int days_=0;
        	try{
        		sqlStr="select count(*) from hotel_customer where name='"+customer_input.getText()+"'";
        		stmt = dbTest.prepareStatement(sqlStr);
	        	rs = stmt.executeQuery();
	        	rs.next();
	        	if(rs.getInt("count(*)")==0) throw new SQLException("등록되지 않은 고객");
	        	if(!checkin_input.getText().matches("^[0-9]{8}$")) throw new StringIndexOutOfBoundsException("잘못된 날짜");
	        	String check = checkin_input.getText().substring(0,4)+"/"+checkin_input.getText().substring(4,6)+"/"+checkin_input.getText().substring(6,8);
	        	try{
	        		sqlStr="select * from reserve where customer_name='"+customer_input.getText()+"'";
	        		stmt = dbTest.prepareStatement(sqlStr);
		        	rs = stmt.executeQuery();
	        		while(rs.next()){
	        			Calendar checkincal = Calendar.getInstance();
	        			try{ //날짜가 겹치는지 체크해서 ischange를 갱신
	        				String[] checkin = check.split("/");
	        				Date reserveindate = format.parse(checkin[0]+"-"+checkin[1]+"-"+checkin[2]); //예약하려는 날짜
	        				Date checkindate = format.parse(rs.getString("checkin")); //예약되어 있는 체크인 날짜
	        				checkincal.setTime(checkindate);
	            			checkincal.add(Calendar.DATE, rs.getInt("days")-1);
	            			Date checkoutdate = checkincal.getTime(); //예약되어 있는 체크아웃 날짜
	            			checkincal.setTime(reserveindate);
	            			checkincal.add(Calendar.DATE, Integer.parseInt(days_input.getSelectedItem()+""));
	            			Date reserveoutdate = checkincal.getTime();
	            			//체크인 날짜 <= 예약 날짜 <= 체크아웃 날짜 (날짜 겹치는 경우 체크)
	            			if((reserveindate.compareTo(checkindate)>=0 && reserveindate.compareTo(checkoutdate)<=0) || (checkindate.compareTo(reserveindate)>=0 && checkindate.compareTo(reserveoutdate)<=0)){
	            				ischange=true;
	            				room_number_ = rs.getString("room_number");
	            				checkin_ = rs.getString("checkin").substring(0,4)+"/"+rs.getString("checkin").substring(5,7)+"/"+rs.getString("checkin").substring(8,10);
	            				days_ = rs.getInt("days");
	            			}
	        			}
	        			catch(ParseException pe){
	        				System.out.println(pe);
	        			}
	        		}
	        		sqlStr="select tel from hotel_customer where name='"+customer_input.getText()+"'";
        			stmt = dbTest.prepareStatement(sqlStr);
		        	rs = stmt.executeQuery();
		        	rs.next();
		        	customer_tel = rs.getString("tel");
		        	if(ischange){ //예약 변경
	        			sqlStr="delete from reserve where customer_name='"+customer_input.getText()+"' and room_number='"+room_number_+"' and checkin='"+checkin_+"' and days="+days_;
	        			stmt = dbTest.prepareStatement(sqlStr);
			        	rs = stmt.executeQuery();
			        	sqlStr="select name,tel from hotel_employee order by DBMS_RANDOM.RANDOM";
		        		stmt = dbTest.prepareStatement(sqlStr);
			        	rs = stmt.executeQuery();
			        	rs.next();
			        	employee_tel = rs.getString("tel");
			        	sqlStr="insert into reserve values('"+customer_tel+"','"+employee_tel+"','"+customer_input.getText()+"','"+rs.getString("name")+"','"+room_input.getSelectedItem()+"','"+check+"',"+Integer.parseInt(days_input.getSelectedItem()+"")+")";
		        		stmt = dbTest.prepareStatement(sqlStr);
			        	rs = stmt.executeQuery();
			        	rs.next();
			        	JOptionPane.showMessageDialog(frame,"예약 변경 되었습니다.");
			        	reserved();
			        	roomcheck();
	        		}
	        		else{ //예약 등록
		        		sqlStr="select name,tel from hotel_employee order by DBMS_RANDOM.RANDOM";
		        		stmt = dbTest.prepareStatement(sqlStr);
			        	rs = stmt.executeQuery();
			        	rs.next();
			        	employee_tel = rs.getString("tel");
			        	sqlStr="insert into reserve values('"+customer_tel+"','"+employee_tel+"','"+customer_input.getText()+"','"+rs.getString("name")+"','"+room_input.getSelectedItem()+"','"+check+"',"+Integer.parseInt(days_input.getSelectedItem()+"")+")";
		        		stmt = dbTest.prepareStatement(sqlStr);
			        	rs = stmt.executeQuery();
			        	rs.next();
			        	JOptionPane.showMessageDialog(frame,"예약 등록 되었습니다.");
			        	reserved();
			        	roomcheck();
	        		}
	        	}
	        	catch(SQLException se){
	        		System.out.println(se);
	        		JOptionPane.showMessageDialog(frame,"잘못된 형식입니다.");
	        	}
        	}
        	catch(SQLException se){
        		JOptionPane.showMessageDialog(frame,"등록되지 않은 고객입니다.");
        		System.out.println(se);
        	}
        	catch(StringIndexOutOfBoundsException ie){
        		JOptionPane.showMessageDialog(frame,"잘못된 날짜입니다. yyyymmdd 형식으로 입력하세요.");
        		System.out.println(ie);
        	}
		}
		else if(e.getSource() == cancle){ //예약취소
			String sqlStr;
        	PreparedStatement stmt;
        	ResultSet rs;
        	try{
        		String check = checkin_input.getText().substring(0,4)+"/"+checkin_input.getText().substring(4,6)+"/"+checkin_input.getText().substring(6,8);
        		sqlStr="delete from reserve where customer_name='"+customer_input.getText()+"' and room_number='"+room_input.getSelectedItem()+"' and checkin='"+check+"' and days="+Integer.parseInt(days_input.getSelectedItem()+"");
        		stmt = dbTest.prepareStatement(sqlStr);
	        	rs = stmt.executeQuery();
	        	rs.next();
	        	JOptionPane.showMessageDialog(frame,"예약취소되었습니다.");
	        	reserved();
	        	roomcheck();
        	}
        	catch(SQLException se){
        		System.out.println(se);
        		JOptionPane.showMessageDialog(frame,"예약 정보가 없습니다.");
        	}
		}
        else if(e.getSource() == customer_list){ //고객조회
        	String result="";
        	String sqlStr;
        	PreparedStatement stmt;
        	ResultSet rs;
        	try{
    			sqlStr = "select name,sex,address,tel from hotel_customer where name='"+customer_name_input.getText()+"'";
    			stmt = dbTest.prepareStatement(sqlStr);
	        	rs = stmt.executeQuery();
	        	rs.next();
        		result += "고객명: "+rs.getString("name");
	        	result += "\n성별: "+rs.getString("sex");
	        	result += "\n주소: "+rs.getString("address");
	        	result += "\n연락처: "+rs.getString("tel");
	        	sqlStr = "select sum(days) from hotel_customer,reserve where name=customer_name and name='"+customer_name_input.getText()+"'";
    			stmt = dbTest.prepareStatement(sqlStr);
	        	rs = stmt.executeQuery();
	        	rs.next();
	        	if(rs.getString("sum(days)") != null) result += "\n총 투숙기간: "+rs.getString("sum(days)");
	        	else result += "\n총 투숙기간: 없음";
	        	sqlStr = "select max(checkin) from hotel_customer h,reserve r where name=customer_name and name='"+customer_name_input.getText()+"'";
    			stmt = dbTest.prepareStatement(sqlStr);
	        	rs = stmt.executeQuery();
	        	rs.next();
	        	if(rs.getString("max(checkin)") != null) result += "\n최근 투숙일: "+rs.getString("max(checkin)").substring(0,10);
	        	else result += "\n최근 투숙일: 없음";
	        	sqlStr = "select employee_name,count(employee_name) from reserve where customer_name='"+customer_name_input.getText()+"' group by (employee_name) having count(employee_name) in (select max(count(employee_name)) from reserve where customer_name='"+customer_name_input.getText()+"' group by (employee_name))";
    			stmt = dbTest.prepareStatement(sqlStr);
	        	rs = stmt.executeQuery();
	        	if(!rs.next()) result += "\n객실전담직원(최다) : 없음";
	        	result += "\n객실전담직원(최다): "+rs.getString("employee_name")+"     ("+rs.getInt("count(employee_name)")+"회)";
	        	customer_info.setText(result);
			}
			catch(SQLException se){
				System.out.println(se);
				if(result == ""){
					customer_info.setText("고객정보가 없습니다.");
				}
				else{
					customer_info.setText(result);
				}
			}
        }
        else if(e.getSource() == room_num_input){ //객실 조회
        	String result="";
        	String todate = today.getText();
        	String sqlStr;
        	PreparedStatement stmt;
        	ResultSet rs;
        	try{
    			sqlStr = "select roomnumber,capacity,type from hotel_room where roomnumber='"+room_num_input.getSelectedItem()+"'";
    			stmt = dbTest.prepareStatement(sqlStr);
	        	rs = stmt.executeQuery();
	        	rs.next();
        		result += "방번호: "+rs.getString("roomnumber");
	        	result += "\n수용인원: "+rs.getString("capacity");
	        	result += "\n타입: "+rs.getString("type");
	        	sqlStr = "select count(*) from reserved where room_number='"+room_num_input.getSelectedItem()+"' and year="+Integer.parseInt(todate.substring(1,5))+" and month="+Integer.parseInt(todate.substring(6,8))+" and day="+Integer.parseInt(todate.substring(9,11));
    			stmt = dbTest.prepareStatement(sqlStr);
	        	rs = stmt.executeQuery();
	        	rs.next();
	        	if(rs.getInt("count(*)")==0){
	        		result += "\n상태: 비어있음";
	        	}
	        	else{
	        		result += "\n상태: 투숙중";
	        	}
	        	sqlStr = "select customer_name,count(customer_name) from reserve where room_number='"+room_num_input.getSelectedItem()+"' group by(customer_name) having count(customer_name) in (select max(count(customer_name)) from reserve where room_number='"+room_num_input.getSelectedItem()+"' group by(customer_name))";
    			stmt = dbTest.prepareStatement(sqlStr);
	        	rs = stmt.executeQuery();
	        	if(!rs.next()) result += "\n투숙고객(최다) : 없음\n객실전담직원(최다) : 없음";
	        	result += "\n투숙고객(최다): "+rs.getString("customer_name")+"     ("+rs.getInt("count(customer_name)")+"회)";
	        	sqlStr = "select employee_name,count(employee_name) from reserve where room_number='"+room_num_input.getSelectedItem()+"' group by (employee_name) having count(employee_name) in (select max(count(employee_name)) from reserve where room_number='"+room_num_input.getSelectedItem()+"' group by (employee_name))";
    			stmt = dbTest.prepareStatement(sqlStr);
	        	rs = stmt.executeQuery();
	        	rs.next();
	        	result += "\n객실전담직원(최다): "+rs.getString("employee_name")+"     ("+rs.getInt("count(employee_name)")+"회)";
	        	room_num_info.setText(result);
			}
			catch(SQLException se){
				System.out.println(se);
				room_num_info.setText(result);
			}
        }
        else if(e.getSource() == employee_list){ //직원 조회
        	String result="";
        	String sqlStr;
        	PreparedStatement stmt;
        	ResultSet rs;
        	try{
    			sqlStr = "select name,sex,address,tel from hotel_employee where name='"+employee_input.getText()+"'";
    			stmt = dbTest.prepareStatement(sqlStr);
	        	rs = stmt.executeQuery();
	        	rs.next();
        		result += "직원명: "+rs.getString("name");
	        	result += "\n성별: "+rs.getString("sex");
	        	result += "\n주소: "+rs.getString("address");
	        	result += "\n연락처: "+rs.getString("tel");
	        	sqlStr = "select customer_name,count(customer_name) from reserve where employee_name='"+employee_input.getText()+"' group by (customer_name) having count(customer_name) in (select max(count(customer_name)) from reserve where employee_name='"+employee_input.getText()+"' group by (customer_name))";
    			stmt = dbTest.prepareStatement(sqlStr);
	        	rs = stmt.executeQuery();
	        	if(!rs.next()) result += "\n접대고객(최다): 없음\n관리객실(최다): 없음";
	        	result += "\n접대고객(최다): "+rs.getString("customer_name")+"     ("+rs.getInt("count(customer_name)")+"회)";
	        	sqlStr = "select room_number,count(room_number) from reserve where employee_name='"+employee_input.getText()+"' group by (room_number) having count(room_number) in (select max(count(room_number)) from reserve where employee_name='"+employee_input.getText()+"' group by (room_number))";
    			stmt = dbTest.prepareStatement(sqlStr);
	        	rs = stmt.executeQuery();
	        	rs.next();
	        	result += "\n관리객실(최다): "+rs.getString("room_number")+"     ("+rs.getInt("count(room_number)")+"회)";
	        	employee_info.setText(result);
			}
			catch(SQLException se){
				System.out.println(se);
				if(result == ""){
					employee_info.setText("직원정보가 없습니다.");
				}
				else{
					employee_info.setText(result);
				}
			}
        }
        else if(e.getSource() == customer_signup){ //고객가입
        	cust_signup.setVisible(true);
        }
        else if(e.getSource() == signup_cu_ok){
        	String sqlStr;
        	PreparedStatement stmt;
        	ResultSet rs;
        	try{
        		sqlStr = "insert into hotel_customer values('"+signup_customer_input.getText()+"','"+signup_cu_sex_input.getSelectedItem()+"','"+signup_cu_address_input.getSelectedItem()+"','"+signup_cu_tel_input.getText()+"')";
        		stmt = dbTest.prepareStatement(sqlStr);
	        	rs = stmt.executeQuery();
	        	rs.next();
	        	signup_customer_input.setText("");
        		signup_cu_tel_input.setText("");
	        	cust_signup.setVisible(false);
	        	JOptionPane.showMessageDialog(frame,"가입 완료 되었습니다.");
        	}
        	catch(SQLException se){
        		signup_customer_input.setText("");
        		signup_cu_tel_input.setText("");
        		System.out.println(se);
        		JOptionPane.showMessageDialog(cust_signup,"이미 가입된 고객입니다.");
        	}
        }
        else if(e.getSource() == signup_cu_cancle){
        	signup_customer_input.setText("");
    		signup_cu_tel_input.setText("");
        	cust_signup.setVisible(false);
        }
        else if(e.getSource() == employee_signup){ //직원가입
        	em_signup.setVisible(true);
        }
        else if(e.getSource() == signup_em_ok){
        	String sqlStr;
        	PreparedStatement stmt;
        	ResultSet rs;
        	try{
        		sqlStr = "insert into hotel_employee values('"+signup_employee_input.getText()+"','"+signup_em_sex_input.getSelectedItem()+"','"+signup_em_address_input.getSelectedItem()+"','"+signup_em_tel_input.getText()+"')";
        		stmt = dbTest.prepareStatement(sqlStr);
	        	rs = stmt.executeQuery();
	        	rs.next();
	        	signup_employee_input.setText("");
        		signup_em_tel_input.setText("");
	        	em_signup.setVisible(false);
	        	JOptionPane.showMessageDialog(frame,"가입 완료 되었습니다.");
        	}
        	catch(SQLException se){
        		signup_employee_input.setText("");
        		signup_em_tel_input.setText("");
        		System.out.println(se);
        		JOptionPane.showMessageDialog(em_signup,"이미 가입된 직원입니다.");
        	}
        }
        else if(e.getSource() == signup_em_cancle){
        	signup_employee_input.setText("");
    		signup_em_tel_input.setText("");
        	em_signup.setVisible(false);
        }
    }
	private void connectDB(){
        try {
        	String sqlStr;
        	PreparedStatement stmt;
        	ResultSet rs;
            Class.forName("oracle.jdbc.OracleDriver");
            dbTest = DriverManager.getConnection("jdbc:oracle:thin:" + "@localhost:1521:XE", username, password);
            frame.setVisible(true);
            reserved();
            sqlStr = "select roomnumber from hotel_room";
			stmt = dbTest.prepareStatement(sqlStr);
        	rs = stmt.executeQuery();
        	int i=0;
        	while(rs.next()){
        		table_cell[i].setText(rs.getString("roomnumber"));
        		room_input.addItem(Integer.parseInt(rs.getString("roomnumber")));
        		room_num_input.addItem(Integer.parseInt(rs.getString("roomnumber")));
        		i++;
        	}
			roomcheck();
        }
        catch(Exception e){
        	System.out.println("1");
            System.out.println(e);
        }
    }
	public void reserved(){ //예약된 방, 날짜를 reserved 테이블에 저장
		String sqlStr,sqlStr_;
    	PreparedStatement stmt,stmt_;
    	ResultSet rs,rs_;
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    	try{ //테이블 초기화
    		sqlStr = "drop table reserved";
			stmt = dbTest.prepareStatement(sqlStr);
        	rs = stmt.executeQuery();
    	}
    	catch(SQLException se){
    		System.out.println("2");
    		System.out.println(se);
    	}
    	
    	try{ //테이블 초기화
        	sqlStr = "create table reserved(customer_name varchar(10) not null,room_number varchar(3) not null,year number(4) not null,month number(2) not null,day number(2) not null)";
			stmt = dbTest.prepareStatement(sqlStr);
        	rs = stmt.executeQuery();
    	}
    	catch(SQLException se){
    		System.out.println("3");
    		System.out.println(se);
    	}
    	try{ //reserved에 데이터 저장
			sqlStr = "select customer_name,room_number,checkin,days from reserve";
			stmt = dbTest.prepareStatement(sqlStr);
        	rs = stmt.executeQuery();
        	Calendar calendar = Calendar.getInstance();
        	while(rs.next()){
        		try{
        			Date to = format.parse(rs.getString("checkin"));
        			calendar.setTime(to);
        		}
        		catch(ParseException pe){
        			System.out.println("4");
        			System.out.println(pe);
        		}
        		for(int i=0; i<rs.getInt("days"); i++){ //checkin과 days를 읽어서 저장해야 될 날짜들을 계산
        			String date = format.format(calendar.getTime());
	        		sqlStr_="insert into reserved values('"+rs.getString("customer_name")+"','"+rs.getString("room_number")+"',"+Integer.parseInt(date.substring(0,4))+","+Integer.parseInt(date.substring(5,7))+","+Integer.parseInt(date.substring(8,10))+")";
	        		stmt_ = dbTest.prepareStatement(sqlStr_);
	            	rs_ = stmt_.executeQuery();
	            	rs_.next();
	            	calendar.add(Calendar.DATE,1);
            	}
        	}
		}
		catch(SQLException se){
			System.out.println("5");
			System.out.println(se);
		}
	}
	public void roomcheck(){ //예약된 방을 노란색으로 표시
		String todate = today.getText();
		String sqlStr;
    	PreparedStatement stmt;
    	ResultSet rs;
    	try{
    		sqlStr="select room_number from reserved where year="+Integer.parseInt(todate.substring(1,5))+" and month="+Integer.parseInt(todate.substring(6,8))+" and day="+Integer.parseInt(todate.substring(9,11));
    		stmt = dbTest.prepareStatement(sqlStr);
        	rs = stmt.executeQuery();
        	for(int i=0; i<20; i++){
        		table_cell[i].setBackground(Color.WHITE);
    		}
        	while(rs.next()){
        		for(int i=0; i<20; i++){
	        		if(table_cell[i].getText().equals(rs.getString("room_number"))) table_cell[i].setBackground(Color.YELLOW);
        		}
        	}
    	}
    	catch(SQLException se){
    		System.out.println("6");
    		System.out.println(se);
    	}
	}
	public static void main(String[] args){
		hotel test = new hotel();
	}
}