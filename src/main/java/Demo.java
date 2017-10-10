package app;

import java.io.IOException;
import java.text.SimpleDateFormat;

import com.jnrsmcu.sdk.netdevice.IDataListener;
import com.jnrsmcu.sdk.netdevice.LoginData;
import com.jnrsmcu.sdk.netdevice.NodeData;
import com.jnrsmcu.sdk.netdevice.RSServer;
import com.jnrsmcu.sdk.netdevice.RealTimeData;
import com.jnrsmcu.sdk.netdevice.StoreData;
import com.jnrsmcu.sdk.netdevice.TelecontrolAck;
import com.jnrsmcu.sdk.netdevice.TimmingAck;

public class Demo {

	public static void main(String[] args) throws IOException,
			InterruptedException {
		RSServer rsServer = RSServer.Initiate(2404);// ��ʼ��
		rsServer.addDataListener(new IDataListener() {// ��Ӽ���
			@Override
			public void receiveTimmingAck(TimmingAck data) {// Уʱָ��Ӧ����
				System.out.println("УʱӦ��->�豸���:" + data.getDeviceId()
						+ "\tִ�н����" + data.getStatus());
			}

			@Override
			public void receiveTelecontrolAck(TelecontrolAck data) {// ң��ָ��Ӧ����
				System.out.println("ң��Ӧ��->�豸���:" + data.getDeviceId()
						+ "\t�̵������:" + data.getRelayId() + "\tִ�н��:"
						+ data.getStatus());
			}

			@Override
			public void receiveStoreData(StoreData data) {// �Ѵ洢���ݽ��մ���
				// �����ڵ����ݡ����ݰ��������豸�������Լ������ڵ����ݡ���ʪ�����ݴ���ڽڵ�������
				for (NodeData nd : data.getNodeList()) {
					SimpleDateFormat sdf = new SimpleDateFormat(
							"yy-MM-dd HH:mm:ss");
					String str = sdf.format(nd.getRecordTime());
					System.out.println("�洢����->�豸��ַ:" + data.getDeviceId()
							+ "\t�ڵ�:" + nd.getNodeId() + "\t�¶�:" + nd.getTem()
							+ "\tʪ��:" + nd.getHum() + "\t�洢ʱ��:" + str);
				}

			}

			@Override
			public void receiveRealtimeData(RealTimeData data) {// ʵʱ���ݽ��մ���
				// �����ڵ����ݡ����ݰ��������豸�������Լ������ڵ����ݡ���ʪ�����ݴ���ڽڵ�������
				for (NodeData nd : data.getNodeList()) {
					System.out.println("ʵʱ����->�豸��ַ:" + data.getDeviceId()
							+ "\t�ڵ�:" + nd.getNodeId() + "\t�¶�:" + nd.getTem()
							+ "\tʪ��:" + nd.getHum() + "\t����:" + data.getLng()
							+ "\tγ��:" + data.getLat() + "\t��������:"
							+ data.getCoordinateType() + "\t�̵���״̬:"
							+ data.getRelayStatus());
				}

			}

			@Override
			public void receiveLoginData(LoginData data) {// ��¼���ݽ��մ���
				System.out.println("��¼->�豸��ַ:" + data.getDeviceId());

			}
		});
		rsServer.start();

	}

}
