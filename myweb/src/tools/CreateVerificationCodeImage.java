package tools;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;



public class CreateVerificationCodeImage {
         private static final int WIDTH=100;//ͼƬ�Ŀ��
         private static final int HEIGHT=30;//ͼƬ�ĸ߶�
         private static final int LENGTH=4;//��֤��ĳ���
         public static final int LINECOUNT=10; //�����ߵĸ���
        
	//��֤����ַ���
	  private static final String str="23456789"
			  +"ABCDEFGHJKLMNPQRSTUVWXYZ"+"abcdefghijklmnpqrstuvwxyz";
	
	private static Random random=new Random();//�����
	
	//������λ�����
	public String createCode(){
		String code="";
		for(int i=0;i<LENGTH;i++){
			//nextInt����һ�������int��ֵ
			char c=str.charAt(random.nextInt(str.length()));
			code=code+c;	
		}
		return code;		
	}
	public Color getColor(){
		return new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255));
	}
	//��ȡ������ʽ
	public Font getFont(){
		return new Font("Fixedsys",Font.CENTER_BASELINE,22);
		
	}
	//�滭�ַ�
	public void drawChar(Graphics g,String code){
		g.setFont(getFont());
		for(int i=0;i<LENGTH;i++){
			char c=code.charAt(i);
			g.setColor(getColor());
			g.drawString(c+"", 20*i+10, 20);			
		}
	}
	//�����漴����
	public void drawLine(Graphics g){
		int x=random.nextInt(WIDTH);
		int y=random.nextInt(HEIGHT);
		int x1=random.nextInt(13);
		int y1=random.nextInt(15);
		g.drawLine(x, y, x1, y1);
	}
	//������֤���ͼƬ
	public BufferedImage CreateImage(String vCode){
		BufferedImage image=new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_3BYTE_BGR);
		Graphics g=image.getGraphics();//�õ�����
		//���ñ���ɫ�����ƾ���
		g.setColor(Color.WHITE);
		g.fillRect(0, 0,WIDTH,HEIGHT);
		//��֤��Ļ���
		drawChar(g,vCode);
		//�����漴��
		for(int i=0;i<LINECOUNT;i++){
			drawLine(g);
		}
		
		//����ͼƬ
		g.dispose();
		//����ͼƬ
		return image;
		
		
	
	}
}
