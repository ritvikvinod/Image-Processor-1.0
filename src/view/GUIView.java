package view;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.Controller;

/**
 * This interface represents the View for this application. This class is being implemented by
 * GUIView to create a graphical user interface.
 */
public class GUIView extends JFrame {
  private JButton buttonBlur;
  private JButton buttonMosaic;
  private JButton buttonSepia;
  private JButton buttonSharpen;
  private JButton buttonGrey;
  private JButton buttonDither;

  private JButton checkerboard;
  private JButton france;
  private JButton greece;
  private JButton swiss;
  private JButton rainbowVertical;
  private JButton rainbowHorizontal;

  private JMenuItem exit;
  private JMenuItem load;
  private JMenuItem save;

  private JPanel imagePanel;
  private JPanel mainPanel;
  private JPanel allButtonPanel;
  private JLabel imageLabel;
  private JLabel filePath;
  private JLabel savePath;
  private JLabel mosaicNum;
  private JLabel checkSquare;
  private JLabel flagLength;
  private JLabel rainbowHeight;
  private JLabel rainbowWidth;

  /**
   * Constructor for setting up the GUI interface before the user can start using the application.
   *
   * @param title The title of the application displayed on the title bar.
   */
  public GUIView(String title) {
    super(title);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    setSize(1000, 600);
    setLocation(100, 100);

    displayWindow();
    mainPanel = new JPanel();
    mainPanel.setLayout(new BorderLayout());
    add(mainPanel);

    displayMenu();

    filePath = new JLabel();
    savePath = new JLabel();
    mosaicNum = new JLabel();
    checkSquare = new JLabel();
    flagLength = new JLabel();
    rainbowHeight = new JLabel();
    rainbowWidth = new JLabel();

    displayImagePanel();

    allButtonPanel = new JPanel();
    allButtonPanel.setLayout(new BoxLayout(allButtonPanel, BoxLayout.Y_AXIS));

    displayProcessImagePanel();
    displayCreateImagePanel();

    mainPanel.add(allButtonPanel, BorderLayout.WEST);

    displayStatusBar();

    setVisible(true);
  }

  private void displayWindow() {
    JWindow window = new JWindow();
    window.setLayout(new BorderLayout());
    window.getContentPane().add(
            new JLabel("", new ImageIcon("res/WindowImage.jpg"), SwingConstants.CENTER));
    window.setBounds(400, 150, 600, 400);
    window.setVisible(true);

    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    window.setVisible(false);
  }

  private void displayMenu() {
    JMenuBar menu = new JMenuBar();
    JMenu fileMenu = new JMenu("File");
    ImageIcon exitIcon = new ImageIcon("res/exit.png");
    ImageIcon saveIcon = new ImageIcon("res/save.png");
    ImageIcon loadIcon = new ImageIcon("res/load.png");

    fileMenu.setMnemonic(KeyEvent.VK_F);
    load = new JMenuItem("Open", loadIcon);
    save = new JMenuItem("Save", saveIcon);
    exit = new JMenuItem("Exit", exitIcon);

    fileMenu.add(load);
    fileMenu.add(save);
    fileMenu.add(exit);
    menu.add(fileMenu);
    mainPanel.add(menu, BorderLayout.NORTH);
  }

  private void displayImagePanel() {
    //show an image with a scrollbar
    imagePanel = new JPanel();
    //a border around the mainPanel with a caption
    imagePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createTitledBorder("Canvas")));
    imagePanel.setLayout(new GridLayout(1, 0, 10, 10));
    //imagePanel.setMaximumSize(null);

    imageLabel = new JLabel();
    JScrollPane imageScrollPane = new JScrollPane(imageLabel);
    imageScrollPane.setPreferredSize(new Dimension(100, 600));
    imagePanel.add(imageScrollPane);
    mainPanel.add(imagePanel, BorderLayout.CENTER);
  }

  private void displayProcessImagePanel() {
    JPanel processImagePanel = new JPanel();
    processImagePanel.setBorder(BorderFactory.createTitledBorder("Process Image Panel"));
    processImagePanel.setLayout(new GridLayout(6, 1));
    buttonBlur = new JButton("Blur");
    buttonDither = new JButton("Dither");
    buttonGrey = new JButton("GrayScale");
    buttonMosaic = new JButton("Mosaic");
    buttonSharpen = new JButton("Sharpen");
    buttonSepia = new JButton("Sepia");

    buttonBlur.setActionCommand("blur");
    buttonSharpen.setActionCommand("sharpen");
    buttonSepia.setActionCommand("sepia");
    buttonGrey.setActionCommand("greyscale");
    buttonDither.setActionCommand("dither");
    buttonMosaic.setActionCommand("mosaic");

    processImagePanel.add(buttonBlur);
    processImagePanel.add(buttonSharpen);
    processImagePanel.add(buttonSepia);
    processImagePanel.add(buttonGrey);
    processImagePanel.add(buttonDither);
    processImagePanel.add(buttonMosaic);
    allButtonPanel.add(processImagePanel);
  }

  private void displayCreateImagePanel() {
    JPanel createPanel = new JPanel();
    createPanel.setBorder(BorderFactory.createTitledBorder("Create Images Panel"));
    createPanel.setLayout(new GridLayout(6, 1));

    checkerboard = new JButton("Checkerboard");
    france = new JButton("France Flag");
    greece = new JButton("Greece Flag");
    swiss = new JButton("Switzerland Flag");
    rainbowVertical = new JButton("Vertical Rainbow");
    rainbowHorizontal = new JButton("Horizontal Rainbow");

    checkerboard.setActionCommand("checkerboard");
    france.setActionCommand("flag-france");
    greece.setActionCommand("flag-greece");
    swiss.setActionCommand("flag-switzerland");
    rainbowHorizontal.setActionCommand("rainbow-horizontal");
    rainbowVertical.setActionCommand("rainbow-vertical");

    createPanel.add(checkerboard);
    createPanel.add(france);
    createPanel.add(greece);
    createPanel.add(swiss);
    createPanel.add(rainbowHorizontal);
    createPanel.add(rainbowVertical);

    allButtonPanel.add(createPanel);
  }

  private void displayStatusBar() {
    JPanel statusPanel = new JPanel();
    statusPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
    mainPanel.add(statusPanel, BorderLayout.SOUTH);
    statusPanel.setLayout(new FlowLayout());
    JLabel statusLabel = new JLabel("Rahul Guin & Ritvik Vinodkumar");
    statusLabel.setHorizontalAlignment(SwingConstants.RIGHT);
    statusPanel.setBackground(new Color(0, 0, 182, 60));
    statusPanel.add(statusLabel);
  }

  public void displayImage(BufferedImage image) {
    imageLabel.setIcon(new ImageIcon(image));
  }

  /**
   * Get the set of feature callbacks that the view can use.
   *
   * @param controllerGUI the set of feature callbacks as a Features object.
   */
  public void setFeatures(Controller controllerGUI) {
    exit.addActionListener(l -> controllerGUI.exitProgram());
    load.addActionListener(l -> openFile(controllerGUI));
    save.addActionListener(l -> saveFile(controllerGUI));
    buttonBlur.addActionListener(l -> controllerGUI.readInput(new Scanner(buttonBlur.getActionCommand())));
    buttonSepia.addActionListener(l -> controllerGUI.readInput(new Scanner(buttonSepia.getActionCommand())));
    buttonSharpen.addActionListener(l -> controllerGUI.readInput(new Scanner(buttonSharpen.getActionCommand())));
    buttonGrey.addActionListener(l -> controllerGUI.readInput(new Scanner(buttonGrey.getActionCommand())));
    buttonDither.addActionListener(l -> controllerGUI.readInput(new Scanner(buttonDither.getActionCommand())));
    buttonMosaic.addActionListener(l -> mosaicNumber(controllerGUI));

    checkerboard.addActionListener(l -> checkerboardSquareSize(controllerGUI));
    france.addActionListener(l -> franceDimension(controllerGUI));
    greece.addActionListener(l -> greeceDimension(controllerGUI));
    swiss.addActionListener(l -> swissDimension(controllerGUI));
    rainbowHorizontal.addActionListener(l -> setRainbowHorizontal(controllerGUI));
    rainbowVertical.addActionListener(l -> setRainbowVertical(controllerGUI));
  }

  private void openFile(Controller controllerGUI) {
    final JFileChooser fchooser = new JFileChooser(".");
    FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "JPG & GIF Images", "jpg", "gif");
    fchooser.setFileFilter(filter);
    int retvalue = fchooser.showOpenDialog(this);
    if (retvalue == JFileChooser.APPROVE_OPTION) {
      File f = fchooser.getSelectedFile();
      filePath.setText(f.getAbsolutePath());
    }
    controllerGUI.loadImage(filePath.getText());
  }

  private void saveFile(Controller controllerGUI) {
    final JFileChooser fchooser = new JFileChooser(".");
    int retvalue = fchooser.showSaveDialog(this);
    if (retvalue == JFileChooser.APPROVE_OPTION) {
      File f = fchooser.getSelectedFile();
      savePath.setText(f.getAbsolutePath());
    }
    controllerGUI.saveImage(savePath.getText());
  }

  private void mosaicNumber(Controller controllerGUI) {
    mosaicNum.setText(JOptionPane.showInputDialog("Please enter the number of mosaics."));
    if (Integer.parseInt(mosaicNum.getText()) <= 0) {
      JOptionPane.showMessageDialog(null, "Seeds cannot be negative.");
      return;
    }
    controllerGUI.readInput(new Scanner(buttonMosaic.getActionCommand() + " " + mosaicNum.getText()));
  }

  private void checkerboardSquareSize(Controller controllerGUI) {
    checkSquare.setText(JOptionPane.showInputDialog("Please enter the side of each square."));
    if (Integer.parseInt(checkSquare.getText()) <= 0) {
      JOptionPane.showMessageDialog(null, "Side cannot be negative.");
      return;
    }
    controllerGUI.readInput(new Scanner("checkerboard " + checkSquare.getText()));
  }

  private void franceDimension(Controller controllerGUI) {
    flagLength.setText(JOptionPane.showInputDialog("Please enter the length of the flag."));
    if (Integer.parseInt(flagLength.getText()) <= 0) {
      JOptionPane.showMessageDialog(null, "Length cannot be negative or zero.");
      return;
    }
    controllerGUI.readInput(new Scanner("flag-france " + flagLength.getText()));
  }

  private void greeceDimension(Controller controllerGUI) {
    flagLength.setText(JOptionPane.showInputDialog("Please enter the length of the flag."));
    if (Integer.parseInt(flagLength.getText()) <= 0) {
      JOptionPane.showMessageDialog(null, "Length cannot be negative or zero.");
      return;
    }
    controllerGUI.readInput(new Scanner("flag-greece " + flagLength.getText()));
  }

  private void swissDimension(Controller controllerGUI) {
    flagLength.setText(JOptionPane.showInputDialog("Please enter the length of the flag."));
    if (Integer.parseInt(flagLength.getText()) <= 0) {
      JOptionPane.showMessageDialog(null, "Length cannot be negative or zero.");
      return;
    }
    controllerGUI.readInput(new Scanner("flag-switzerland " + flagLength.getText()));
  }

  private void setRainbowHorizontal(Controller controllerGUI) {
    JTextField height = new JTextField(5);
    JTextField width = new JTextField(5);

    JPanel myPanel = new JPanel();
    myPanel.add(new JLabel("Enter the dimensions of the rainbow."));
    myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));
    JPanel inputFields = new JPanel();
    inputFields.add(new JLabel("Height:"));
    inputFields.add(height);
    inputFields.add(Box.createHorizontalStrut(15)); // a spacer
    inputFields.add(new JLabel("Width:"));
    inputFields.add(width);
    myPanel.add(inputFields);
    JOptionPane.showConfirmDialog(null, myPanel, "Input", JOptionPane.OK_CANCEL_OPTION);
    if (Integer.parseInt(height.getText()) <= 0 || Integer.parseInt(width.getText()) <= 0) {
      JOptionPane.showMessageDialog(null, "Dimensions cannot be negative or zero.");
      return;
    }
    controllerGUI.readInput(new Scanner("rainbow-horizontal " + height.getText() + " " + width.getText()));
  }

  private void setRainbowVertical(Controller controllerGUI) {
    JTextField height = new JTextField(5);
    JTextField width = new JTextField(5);

    JPanel myPanel = new JPanel();
    myPanel.add(new JLabel("Enter the dimensions of the rainbow."));
    myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));
    JPanel inputFields = new JPanel();
    inputFields.add(new JLabel("Height:"));
    inputFields.add(height);
    inputFields.add(Box.createHorizontalStrut(15)); // a spacer
    inputFields.add(new JLabel("Width:"));
    inputFields.add(width);
    myPanel.add(inputFields);
    JOptionPane.showConfirmDialog(null, myPanel, "Input", JOptionPane.OK_CANCEL_OPTION);
    if (Integer.parseInt(height.getText()) <= 0 || Integer.parseInt(width.getText()) <= 0) {
      JOptionPane.showMessageDialog(null, "Dimensions cannot be negative or zero.");
      return;
    }
    controllerGUI.readInput(new Scanner("rainbow-vertical " + height.getText() + " " + width.getText()));
  }
}
