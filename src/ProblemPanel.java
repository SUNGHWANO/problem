import java.awt.Button;
import java.awt.Choice;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.List;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;


@SuppressWarnings("serial")
public class ProblemPanel extends ContentPanel{

	ProblemControl controller = new ProblemControl();



	TextField tfTitle = new TextField(10);
	TextField tfTcher = new TextField(10);
	TextField tfStdata = new TextField(10);
	TextField tfEnddata = new TextField(10);
	
	
	
	Choice tfClassroom = new Choice();

	
	
	
	
	Choice tfManager = new Choice();




	Panel newButtonbar = new Panel();
	Panel detailButtonbar = new Panel();

	Button btnAdd = new Button("등록");
	Button btnUpdate = new Button("변경");
	Button btnDelete = new Button("삭제");
	Button btnCancel = new Button("취소");


	int selectIndex = -1;




	List listView = new List(){
		public Dimension getPreferredSize(){
			return new Dimension(300, 450);
		};
	};


	Panel detailView = new Panel(new FlowLayout(FlowLayout.LEFT));


	public ProblemPanel() {
		super("과제");



		listView.setMultipleMode(false);
		listView.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				Problem p = Problem.FromCSV(listView.getItem(listView.getSelectedIndex()));
				tfTitle.setText(p.title);
				tfTcher.setText(p.teacher);
				tfStdata.setText(p.startData);
				tfEnddata.setText(p.endData);
				tfClassroom.select(p.classRoom);
				tfManager.select(p.manager);

				newButtonbar.setVisible(false);
				detailButtonbar.setVisible(true);
				selectIndex = listView.getSelectedIndex();
			}
		});
		content.add(listView);


		detailView.setPreferredSize(new Dimension(300, 450));
		content.add(detailView);


		Panel rowPane = new Panel(new FlowLayout(FlowLayout.LEFT));
		rowPane.setPreferredSize(new Dimension(190, 50));
		Label lTitle = new Label("과목");
		lTitle.setPreferredSize(new Dimension(90, 50));
		rowPane.add(lTitle);
		rowPane.add(tfTitle);
		detailView.add(rowPane);

		rowPane = new Panel(new FlowLayout(FlowLayout.LEFT));
		rowPane.setPreferredSize(new Dimension(190, 50));
		Label lTcher = new Label("강사");
		lTcher.setPreferredSize(new Dimension(90, 50));
		rowPane.add(lTcher);
		rowPane.add(tfTcher);
		detailView.add(rowPane);

		rowPane = new Panel(new FlowLayout(FlowLayout.LEFT));
		rowPane.setPreferredSize(new Dimension(190, 50));
		Label lStdata = new Label("시작일");
		lStdata.setPreferredSize(new Dimension(90, 50));
		rowPane.add(lStdata);
		rowPane.add(tfStdata);
		detailView.add(rowPane);

		rowPane = new Panel(new FlowLayout(FlowLayout.LEFT));
		rowPane.setPreferredSize(new Dimension(190, 50));
		Label lEnddata = new Label("종료일");
		lEnddata.setPreferredSize(new Dimension(90, 50));
		rowPane.add(lEnddata);
		rowPane.add(tfEnddata);
		detailView.add(rowPane);

		rowPane = new Panel(new FlowLayout(FlowLayout.LEFT));
		rowPane.setPreferredSize(new Dimension(190, 50));
		Label lClassroom = new Label("강의실");
		lClassroom.setPreferredSize(new Dimension(90, 50));
		rowPane.add(lClassroom);
		rowPane.add(tfClassroom);
		detailView.add(rowPane);

		rowPane = new Panel(new FlowLayout(FlowLayout.LEFT));
		rowPane.setPreferredSize(new Dimension(190, 50));
		Label lManager = new Label("관리자");
		lManager.setPreferredSize(new Dimension(90, 50));
		rowPane.add(lManager);
		rowPane.add(tfManager);
		detailView.add(rowPane);






		newButtonbar = new Panel(new FlowLayout(FlowLayout.LEFT));
		newButtonbar.setPreferredSize(new Dimension(150, 50));
		newButtonbar.add(btnAdd);


		btnAdd.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Problem p = new Problem();
				p.title = tfTitle.getText();
				p.teacher = tfTcher.getText();
				p.startData = tfStdata.getText();
				p.endData = tfEnddata.getText();
				p.classRoom = tfClassroom.getSelectedItem();
				p.manager = tfManager.getSelectedItem();
				
				controller.add(p);
				
				listView.add(p.toString());
			
				clearForm();
				
			}
		});
		
		




		detailView.add(newButtonbar);







		detailButtonbar = new Panel(new FlowLayout(FlowLayout.LEFT));
		detailButtonbar.setPreferredSize(new Dimension(150, 50));
		detailButtonbar.add(btnUpdate);
		detailButtonbar.add(btnDelete);
		detailButtonbar.add(btnCancel);
	
		
		
			btnUpdate.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				Problem p = new Problem();
				p.title = tfTitle.getText();
				p.teacher= tfTcher.getText();
				p.startData = tfStdata.getText();
				p.endData = tfEnddata.getText();
				p.classRoom = tfClassroom.getSelectedItem();
				p.manager = tfManager.getSelectedItem();
				
				controller.update(selectIndex, p);
				listView.replaceItem(p.toString(), selectIndex);
				
			}
		});
		
			btnDelete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					newButtonbar.setVisible(true);
					detailButtonbar.setVisible(false);
					listView.remove(selectIndex);
					content.remove(selectIndex);
					clearForm();
				}
			});
			
			btnCancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					newButtonbar.setVisible(true);
					detailButtonbar.setVisible(false);
					selectIndex = -1;
					clearForm();
				}
			});
		
		
		content.add(detailView);
		detailView.add(detailButtonbar);
		
		controller.load();
		displayList();





	}
	
	private void displayList() {
	  for (Problem p : controller.problemList) {
	  	listView.add(p.toString());
	  }
  }
	
	public void clearForm(){
		tfTitle.setText("");
		tfTcher.setText("");
		tfStdata.setText("");
		tfEnddata.setText("");
		tfClassroom.select("");
		tfManager.select("");
		
	}
	
	
	public void save(){
		controller.save();
	}
}
