package pt.ornrocha.rencoder.gui.components.panels.info;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;

import org.pmw.tinylog.Logger;

import pt.ornrocha.rencoder.mediafiles.setfiles.foldersandfiles.ListFiles;


/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class MessageWarnDialog extends JDialog{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ImageIcon icon;
	private String msg="";
	private String title="";
	private JFrame mainframe;
	
	public MessageWarnDialog(JFrame parent,String msg, String title, ImageIcon icon){
		this.msg=msg;
		this.icon=icon;
		this.mainframe=parent;
		this.title=title;
		initGUI();
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		if(parent!=null)
		   setLocationRelativeTo(mainframe);
	}
	
	
	private void initGUI() {
		try {
		
			getContentPane().setLayout(new BoxLayout(this.getContentPane(),BoxLayout.X_AXIS));
			{
				this.setSize(283, 149);
				setTitle(title);
				if(icon!=null)
					add(new JLabel(icon));
				    JLabel msgl =new JLabel("<html>"+convertToMultiline(msg)+"</html>");
				    msgl.setFont(UIManager.getFont("Label.font"));
				    //msgl.setFont(new Font("Serif", Font.BOLD, 15));
				    msgl.setHorizontalAlignment(JLabel.CENTER);
				    add(msgl );
				    if(mainframe!=null){
				    	setIconImage(mainframe.getIconImage());
				    	
				    }
	
			}
		} catch(Exception e) {
		    Logger.error(e);
		}
	}
	
	public static void main(String[] args) {
		MessageWarnDialog m = new MessageWarnDialog(null, "fgsfdgsdfgsdfgdsfg\ndsfgdsfgdsfgdfgds\nfgdsfgdsfgdsfgdsfgdg", "text", new ImageIcon(ListFiles.getIconAbsolutePath("icons/warning64x64.png")));
		m.setVisible(true);
		
	}
	
	private String convertToMultiline(String orig)
	{
	    return orig.replaceAll("\n", "<br>");
	}

}
