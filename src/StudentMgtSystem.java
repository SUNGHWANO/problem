

/* 
 * 
 */
import java.awt.CardLayout;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/* Custom Event 다루기
 * - 이벤트 처리 방식을 사용하여 쌍방향 참조를 제거 
 * 1) Event 정의
 * - StateChangeEvent 클래스 생성
 * 2) Event Listener 정의
 * - 이벤트가 발생했을 때 호출할 메서드를 정의
 * - caller와 callee 사이의 호출 규칙은 인터페이스 문법으로 정의한다.
 * - 인터페이스란? Caller와 Callee 사이의 호출 규칙이다.
 * - 추상클래스란? 하위 클래스들에게 상속해 줄 공통 속성과 공통 기능을 정의한 클래스이다.
 * - 추상메서드란? 하위 클래스에서 반드시 정의해야 하는 메서드이다.
 */
@SuppressWarnings("serial")
public class StudentMgtSystem extends Frame {
	public static final String MENU_PANEL = "MenuPanel";
	public static final String STUDENT_PANEL = "StudentPanel";
	public static final String SCORE_PANEL = "ScorePanel";
	public static final String PROBLEM_PANEL = "problemPanel";
	MenuPanel menuPanel;
	StudentPanel studentPanel;
	ScorePanel scorePanel;
	ProblemPanel problemPanel;
	
	// StateChangeListener를 구현한 익명 클래스 정의 
	StateChangeListener stateChangeListener = new StateChangeListener() {
		public void stateChanged(StateChangeEvent e) {
			CardLayout cardLayout = 
					(CardLayout)StudentMgtSystem.this.getLayout();
			if (e.stateName.equals("back")) {
				cardLayout.show(StudentMgtSystem.this, MENU_PANEL);
				
			} else if (e.stateName.equals("studentPanel")) {
				cardLayout.show(StudentMgtSystem.this, STUDENT_PANEL);
				
			} else if (e.stateName.equals("scorePanel")) {
				cardLayout.show(StudentMgtSystem.this, SCORE_PANEL);
			} else if (e.stateName.equals("problemPanel")){
				cardLayout.show(StudentMgtSystem.this, PROBLEM_PANEL);
			}
    }
	};
	
	public StudentMgtSystem() {
		super("학생관리시스템"); 
		setSize(800, 600);
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				studentPanel.save();
				scorePanel.save();
				problemPanel.save();
				System.exit(0); 		
			}
		});
		
		setLayout(new CardLayout()); // 메뉴화면,학생관리화면,점수관리화면 겹치게 함.
		
		menuPanel = new MenuPanel();
		studentPanel = new StudentPanel();
		scorePanel = new ScorePanel();
		problemPanel = new ProblemPanel();
		
		menuPanel.addStateChangeListener(stateChangeListener);
		studentPanel.addStateChangeListener(stateChangeListener);
		scorePanel.addStateChangeListener(stateChangeListener);
		problemPanel.addStateChangeListener(stateChangeListener);
		
		// CardLayout인 경우 자식 컴포넌트를 붙일 때 
		// 이름을 함께 주어야 한다.
		add(menuPanel, MENU_PANEL);
		add(studentPanel, STUDENT_PANEL);
		add(scorePanel, SCORE_PANEL);
		add(problemPanel, PROBLEM_PANEL);
		
	}
	
	public static void main(String[] args) {
		StudentMgtSystem f = new StudentMgtSystem();
		f.setVisible(true);
	}
	

}













