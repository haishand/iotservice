import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.jnrsmcu.sdk.netdevice.IDataListener;
import com.jnrsmcu.sdk.netdevice.LoginData;
import com.jnrsmcu.sdk.netdevice.NodeData;
import com.jnrsmcu.sdk.netdevice.RSServer;
import com.jnrsmcu.sdk.netdevice.RealTimeData;
import com.jnrsmcu.sdk.netdevice.StoreData;
import com.jnrsmcu.sdk.netdevice.TelecontrolAck;
import com.jnrsmcu.sdk.netdevice.TimmingAck;

public class SwingDemo extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7855826301914463533L;
	private JTextField txtPort;
	private JScrollPane scrollPane;
	private JTextArea textArea;
	private JButton btnStart;
	private JButton btnStop;
	private JCheckBox chkRelay0;
	private JCheckBox chkRelay1;
	private JCheckBox chkRelay2;
	private JCheckBox chkRelay3;
	private JCheckBox chkRelay4;
	private JCheckBox chkRelay5;
	private JCheckBox chkRelay6;
	private JCheckBox chkRelay7;
	private JButton btnTimming;
	private JButton btnCallStore;
	private RSServer rsServer;// ��������������
	private IDataListener listener = new IDataListener() {

		@Override
		public void receiveTimmingAck(TimmingAck data) {// Уʱָ��Ӧ����
			textArea.append("УʱӦ��->�豸���:" + data.getDeviceId() + "\tִ�н����"
					+ data.getStatus() + "\r\n");
		}

		@Override
		public void receiveTelecontrolAck(TelecontrolAck data) {// ң��ָ��Ӧ����
			textArea.append("ң��Ӧ��->�豸���:" + data.getDeviceId() + "\t�̵������:"
					+ data.getRelayId() + "\tִ�н��:" + data.getStatus() + "\r\n");
		}

		@Override
		public void receiveStoreData(StoreData data) {// �Ѵ洢���ݽ��մ���
			// �����ڵ����ݡ����ݰ��������豸�������Լ������ڵ����ݡ���ʪ�����ݴ���ڽڵ�������
			for (NodeData nd : data.getNodeList()) {
				SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
				String str = sdf.format(nd.getRecordTime());

				textArea.append("�洢����->�豸��ַ:" + data.getDeviceId() + "\t�ڵ�:"
						+ nd.getNodeId() + "\t�¶�:" + nd.getTem() + "\tʪ��:"
						+ nd.getHum() + "\t�洢ʱ��:" + str + "\r\n");
			}

		}

		@Override
		public void receiveRealtimeData(RealTimeData data) {// ʵʱ���ݽ��մ���
			// �����ڵ����ݡ����ݰ��������豸�������Լ������ڵ����ݡ���ʪ�����ݴ���ڽڵ�������
			for (NodeData nd : data.getNodeList()) {
				textArea.append("ʵʱ����->�豸��ַ:" + data.getDeviceId() + "\t�ڵ�:"
						+ nd.getNodeId() + "\t�¶�:" + nd.getTem() + "\tʪ��:"
						+ nd.getHum() + "\t���ȣ�" + data.getLng() + "\tγ�ȣ�"
						+ data.getLat() + "\t�������ͣ�" + data.getCoordinateType()
						+ "\t�̵���״̬" + data.getRelayStatus() + "\r\n");
			}

		}

		@Override
		public void receiveLoginData(LoginData data) {// ��¼���ݽ��մ���
			textArea.append("��¼->�豸��ַ:" + data.getDeviceId() + "\r\n");

		}
	};

	private JTextField txtDeviceId;

	public SwingDemo() {
		setTitle("Demo");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 400);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("\u7AEF\u53E3:");
		lblNewLabel.setBounds(10, 10, 40, 15);
		getContentPane().add(lblNewLabel);

		txtPort = new JTextField();
		txtPort.setText("2404");
		txtPort.setBounds(45, 7, 66, 21);
		getContentPane().add(txtPort);
		txtPort.setColumns(10);

		btnStart = new JButton("\u542F\u52A8");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				rsServer = RSServer.Initiate(Integer.parseInt(txtPort.getText()));// ��ʼ��
				rsServer.addDataListener(listener);// ������ݼ����¼�
				try {
					rsServer.start();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}// ������������
			}

		});
		btnStart.setBounds(135, 6, 85, 23);
		getContentPane().add(btnStart);

		btnStop = new JButton("\u505C\u6B62");
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					rsServer.stop();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		btnStop.setBounds(237, 6, 85, 23);
		getContentPane().add(btnStop);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 118, 974, 234);
		getContentPane().add(scrollPane);

		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);

		JLabel label = new JLabel("\u8BBE\u5907\u5730\u5740:");
		label.setBounds(10, 48, 66, 15);
		getContentPane().add(label);

		txtDeviceId = new JTextField();
		txtDeviceId.setText("10000000");
		txtDeviceId.setBounds(75, 45, 84, 21);
		getContentPane().add(txtDeviceId);
		txtDeviceId.setColumns(10);

		chkRelay0 = new JCheckBox("\u7EE7\u7535\u56680");
		chkRelay0.addItemListener(new ChkItemListener(0));
		chkRelay0.setBounds(179, 44, 84, 23);
		getContentPane().add(chkRelay0);

		chkRelay1 = new JCheckBox("\u7EE7\u7535\u56681");
		chkRelay1.addItemListener(new ChkItemListener(1));
		chkRelay1.setBounds(275, 44, 84, 23);
		getContentPane().add(chkRelay1);

		chkRelay2 = new JCheckBox("\u7EE7\u7535\u56682");
		chkRelay2.addItemListener(new ChkItemListener(2));
		chkRelay2.setBounds(371, 44, 84, 23);
		getContentPane().add(chkRelay2);

		chkRelay3 = new JCheckBox("\u7EE7\u7535\u56683");
		chkRelay3.addItemListener(new ChkItemListener(3));
		chkRelay3.setBounds(467, 44, 84, 23);
		getContentPane().add(chkRelay3);

		chkRelay4 = new JCheckBox("\u7EE7\u7535\u56684");
		chkRelay4.addItemListener(new ChkItemListener(4));
		chkRelay4.setBounds(563, 44, 84, 23);
		getContentPane().add(chkRelay4);

		chkRelay5 = new JCheckBox("\u7EE7\u7535\u56685");
		chkRelay5.addItemListener(new ChkItemListener(5));
		chkRelay5.setBounds(659, 44, 84, 23);
		getContentPane().add(chkRelay5);

		chkRelay6 = new JCheckBox("\u7EE7\u7535\u56686");
		chkRelay6.addItemListener(new ChkItemListener(6));
		chkRelay6.setBounds(755, 44, 84, 23);
		getContentPane().add(chkRelay6);

		chkRelay7 = new JCheckBox("\u7EE7\u7535\u56687");
		chkRelay7.addItemListener(new ChkItemListener(7));
		chkRelay7.setBounds(851, 44, 84, 23);
		getContentPane().add(chkRelay7);

		btnTimming = new JButton("\u6821\u65F6");
		btnTimming.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int deviceId = Integer.parseInt(txtDeviceId.getText());
				rsServer.timming(deviceId);
			}
		});
		btnTimming.setBounds(802, 85, 85, 23);
		getContentPane().add(btnTimming);

		btnCallStore = new JButton("\u53EC\u5524\u6570\u636E");
		btnCallStore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int deviceId = Integer.parseInt(txtDeviceId.getText());

				rsServer.callStoreData(deviceId);
			}
		});
		btnCallStore.setBounds(894, 85, 90, 23);
		getContentPane().add(btnCallStore);
	}

	class ChkItemListener implements ItemListener {

		private int relayId = 0;

		public ChkItemListener(int relayId) {
			this.relayId = relayId;
		}

		@Override
		public void itemStateChanged(ItemEvent e) {
			JCheckBox jcb = (JCheckBox) e.getItem();
			int deviceId = Integer.parseInt(txtDeviceId.getText());
			if (jcb.isSelected()) {

				try {
					rsServer.telecontrol(deviceId, relayId, 0, 0);
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			} else {
				try {
					rsServer.telecontrol(deviceId, relayId, 1, 0);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		new SwingDemo().setVisible(true);

	}

}
