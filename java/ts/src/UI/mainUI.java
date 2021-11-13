package UI;
import javax.swing.*;
import javax.swing.colorchooser.DefaultColorSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.*;
import fileTextGet.*;
import index.fileIndex;
import indexGet.fileList;
import fileOpen.fileOpen;


public class mainUI{
  JFrame f = new JFrame("������������");
  //������-------------------------------------------------------------------------------------------------------
  //���� ������������ť����������
  // �½�����
  JButton index = new JButton("�½�����");
  //������
  JTextField SearchText = new JTextField(70);
  //������ť
  JButton SearchButton = new JButton("����");

  //��������
  JComboBox<String> SearchType = new JComboBox<String>();
  //JTable������
  //��������Ϣ����
  private Vector title = new Vector(2);
  //�洢����
  private Vector<Vector> data = new Vector<>();
  //����ģʽ ģ�� ��ȷ
  String selectModel;
  String keyWord;
  //�ı��������
  //�ı�����
  JTextArea TextContent= new JTextArea(40, 40);
  //���ı�������ӹ�����
  JScrollPane ScrollT = new JScrollPane(TextContent);
  //����
  Highlighter TextLighter = TextContent.getHighlighter();
  DefaultHighlighter.DefaultHighlightPainter p = new DefaultHighlighter.DefaultHighlightPainter(Color.YELLOW);
  boolean isHighlighted=true;
  Box topBox = Box.createHorizontalBox();
  Box t = Box.createVerticalBox();
  Box s = Box.createVerticalBox();
  fileIndex fI=new fileIndex(".\\index");
  //highLightLenth �Ǹ������ı����±�ͳ���
  Map<Integer, Integer> highLightLenth=new HashMap<>();
  //��¼�±�
  Vector<Integer> highLightIndex=new Vector<>();
  int nowIndex=1;
  Map<String, String> searchPaths;
  JCheckBox txt = new JCheckBox("TXT");
  JCheckBox word = new JCheckBox("WORD");
  JCheckBox pdf = new JCheckBox("PDF");
  JCheckBox ppt = new JCheckBox("PPT");
  JCheckBox excel = new JCheckBox("EXCEL");
  JCheckBox all = new JCheckBox("All");
  JTextField page = new JTextField(5);//ҳ��
  JButton up = new JButton("��");//��һҳ ���͡�
  JButton down = new JButton("��");//��һҳ
  JButton highLight = new JButton("����");//����
  JButton colorChoose = new JButton("��ɫ");//��ɫѡ��
  //fileFormatSelectionBox������ļ���ʽѡ��Ŀ����ڵ�box
  Box fileFormatSelectionBox =Box.createHorizontalBox();
  //textContentSelectionBox�ǵ���������ֵ�Box,���ڿ��ѡ����ҵ��ı�
  Box textContentSelectionBox =Box.createHorizontalBox();
  public void init(){
    title.add("�ļ���");
    title.add("·��");
    SearchButton.setMnemonic(KeyEvent.VK_ENTER);
    DefaultTableModel model = new DefaultTableModel(data, title){
      @Override
      public boolean isCellEditable(int row, int column) {
        return false;
      }
    };//���ò��ɱ༭
    JTable SearchContent = new JTable(model);
    TableColumn column = SearchContent.getColumnModel().getColumn(0);
    column.setMinWidth(150);
    column.setMaxWidth(800);
    //������������ӹ�����
    JScrollPane ScrollS = new JScrollPane(SearchContent);
    //���ñ��߶�
    SearchContent.setRowHeight(25);
    //���ñ������
    SearchContent.setFont(new Font("������",Font.PLAIN,20));
    //���ñ�ͷ����
    SearchContent.getTableHeader().setFont(new Font("������",Font.BOLD,22));
    //����ѡ�еı�����ɫ
    SearchContent.setSelectionBackground(Color.GRAY);
    s.add(fileFormatSelectionBox);
    s.add(ScrollS);

    SearchContent.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {//SearchContent������˫���¼����ֱ���ļ����ļ�Ŀ¼
        int row = SearchContent.getSelectedRow();
        int col = SearchContent.getSelectedColumn();
        Object path=model.getValueAt(row,1);
        if(e.getClickCount()==2){
          if(col==0){
            //���ļ�
            fileOpen.openFile((String) path);
          }
          else if(col==1){
            fileOpen.openDir((String) path);
          }
        }
        else if(e.getClickCount()==1){
          highLightIndex.clear();
          highLightLenth.clear();
          textContentSelectionBox.setVisible(true);
          TextContent.setText(searchPaths.get(path));
          //�Ƴ�����
          TextLighter.removeAllHighlights();
          setHighlight(keyWord);
          if(!isHighlighted) {
            TextLighter.removeAllHighlights();
          }
          TextContent.setCaretPosition(highLightIndex.get(0));
          nowIndex = 1;
          page.setText(nowIndex + "/" + highLightIndex.size());
        }
      }
    });
    //��װ����
    addTop();
    addBox2();
    //����ѡ�����Ӧ
    setCheckBox();
    //������ť�¼���Ӧ
    SearchButton.addActionListener((e)->{
      //����textContentSelectionBox���ɼ�
      textContentSelectionBox.setVisible(false);
      //����ı�������
      TextContent.setText("");
      keyWord= SearchText.getText();
      if (keyWord.isEmpty()) {
        JOptionPane.showMessageDialog(f, "��δ������������!", "����",
                JOptionPane.ERROR_MESSAGE);
        return;
      }
      else {
        try {
          fI.readFileText(keyWord);
          model.setRowCount(0);
          searchPaths=fI.getSearchPaths();
          if(searchPaths.size()==0){
            JOptionPane.showMessageDialog(f, "δ���ҵ���Ϣ", "��ʾ",
                    JOptionPane.ERROR_MESSAGE);
            return;
          }
          selectModel= (String) SearchType.getSelectedItem();
          if(selectModel.equals("��׼����")) {
            for(Map.Entry<String, String> entry :searchPaths.entrySet()){
              if(isSelected(entry.getKey())&&entry.getValue().contains(keyWord)){
                Vector t = new Vector();
                t.add(getFileName(entry.getKey()));
                t.add(entry.getKey());
                data.add(t);
              }
            }
          }
          else if(selectModel.equals("ģ������")){
            for(Map.Entry<String, String> entry :searchPaths.entrySet()){
              if(isSelected(entry.getKey())){
                Vector t = new Vector();
                t.add(getFileName(entry.getKey()));
                t.add(entry.getKey());
                data.add(t);
              }
            }
          }
          } catch (Exception ioException) {
            ioException.printStackTrace();
          }
      }
      if(data.isEmpty()){
        JOptionPane.showMessageDialog(f, "δ�ܲ��ҵ�����", "��ʾ",
                JOptionPane.ERROR_MESSAGE);
      }
      SearchContent.validate();
      SearchContent.updateUI();
    });
    // ���Index��ť���½�����
    index.addActionListener((e)->{
      JFileChooser fd=new JFileChooser();
      fd.setCurrentDirectory(new File(".\\src/main/resources"));
      fd.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
      fd.showOpenDialog(null);
      File f=fd.getSelectedFile();
      //��ȡ���ļ����µ������ļ�
      fileList fileList= null;
      try {
        fileList = new fileList(f.toString());
      } catch (IOException ioException) {
        ioException.printStackTrace();
      }
      ArrayList<String> arrayList=fileList.getFiles();
      JProgressBarDemo frame=new JProgressBarDemo(arrayList);
      //frame.setBounds(300,200,300,150);    //���������Ĵ�С
      frame.setVisible(true);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.pack();
    });
    //��װ�������ݺ��ı�����
    addSearchAndTextContent();
    setTextContentSelectionBoxEvent();
  }
  private void addBox2(){
    //��װ
    //��ӽ�fileFormatSelectionBox
    fileFormatSelectionBox.add(all);
    fileFormatSelectionBox.add(txt);
    fileFormatSelectionBox.add(word);
    fileFormatSelectionBox.add(ppt);
    fileFormatSelectionBox.add(pdf);
    fileFormatSelectionBox.add(excel);
    //��ӽ�textContentSelectionBox
    textContentSelectionBox.setVisible(false);
    page.setPreferredSize(new Dimension(5,5));
    textContentSelectionBox.add(page);
    textContentSelectionBox.add(Box.createHorizontalStrut(30));
    textContentSelectionBox.add(up);
    textContentSelectionBox.add(down);
    textContentSelectionBox.add(Box.createHorizontalStrut(15));
    textContentSelectionBox.add(highLight);
    textContentSelectionBox.add(Box.createHorizontalStrut(10));
    textContentSelectionBox.add(colorChoose);
    textContentSelectionBox.add(Box.createHorizontalStrut(29));
    all.setSelected(true);
    txt.setSelected(true);
    word.setSelected(true);
    pdf.setSelected(true);
    ppt.setSelected(true);
    excel.setSelected(true);
    textContentSelectionBox.add(Box.createVerticalStrut(10));
    //��������
    all.setFont(new Font("������",Font.BOLD,14));
    txt.setFont(new Font("������",Font.BOLD,14));
    ppt.setFont(new Font("������",Font.BOLD,14));
    pdf.setFont(new Font("������",Font.BOLD,14));
    word.setFont(new Font("������",Font.BOLD,14));
    excel.setFont(new Font("������",Font.BOLD,14));
  }
  //Ѱ���ı������еĹؼ��ʣ���������
  private void setHighlight(String keys){
    highLightIndex.clear();
    highLightLenth.clear();
    try {
      String text = TextContent.getText().toLowerCase();
      int lenth=text.length();
      String key;
      if(selectModel.equals("ģ������")){
        for(int i=0;i<lenth;i++){
          key= String.valueOf(text.charAt(i));
          if(keys.contains(key)){
            //��¼�������ı���λ�úͳ���
            highLightIndex.add(i);
            highLightLenth.put(i,1);
            TextLighter.addHighlight(i,i+1,p);
          }
        }
      }
      else if(selectModel.equals("��׼����")) {
        int keysLenth=keys.length();
        for(int i=0;i<lenth-keysLenth-1;i++){
          key= text.substring(i,i+keysLenth);
          if(keys.equals(key)){
            highLightIndex.add(i);
            highLightLenth.put(i,key.length());
            TextLighter.addHighlight(i,i+keysLenth,p);
          }
        }
      }
    }catch (BadLocationException e) {
      e.printStackTrace();
    }
  }
  private void addTop(){
    SearchType.addItem("ģ������");
    SearchType.addItem("��׼����");
    SearchText.setFont(new Font("����",Font.BOLD,23));
    topBox.add(Box.createHorizontalStrut(10));
    topBox.add(index);
    topBox.add(Box.createHorizontalStrut(14));
    topBox.add(SearchText);
    topBox.add(Box.createHorizontalStrut(14));
    topBox.add(SearchButton);
    topBox.add(Box.createHorizontalStrut(10));
    topBox.add(SearchType);
    topBox.add(Box.createHorizontalStrut(10));
    f.add(topBox, BorderLayout.NORTH);
  }
  private void addSearchAndTextContent(){
    // �����ı�����Զ�����
    TextContent.setLineWrap(true);
    //����в�����
    TextContent.setWrapStyleWord(true);
    TextContent.setFont(new Font("����",Font.BOLD,18));
    t.add(textContentSelectionBox);
    t.add(ScrollT);
    //��ӽ�Frame
    f.add(s, BorderLayout.CENTER);
    f.add(t, BorderLayout.EAST);
    //default Setting
    f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    f.pack();
    f.setBounds(0,0,1000, 800);
    f.setVisible(true);
    f.setLocationRelativeTo(null);
  }
  private void setCheckBox(){
    //��all����¼�
    all.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if(!all.isSelected()){
          txt.setSelected(false);
          pdf.setSelected(false);
          ppt.setSelected(false);
          word.setSelected(false);
          excel.setSelected(false);
        }
        else{
          txt.setSelected(true);
          pdf.setSelected(true);
          ppt.setSelected(true);
          word.setSelected(true);
          excel.setSelected(true);
        }
      }
    });
    //�� txt word PDF PPT Excel ����¼�
    txt.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if(all.isSelected()){
          txt.setSelected(true);
          all.setSelected(false);
          pdf.setSelected(false);
          ppt.setSelected(false);
          word.setSelected(false);
          excel.setSelected(false);
        }
      }
    });
    word.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if(all.isSelected()){
          word.setSelected(true);
          all.setSelected(false);
          pdf.setSelected(false);
          ppt.setSelected(false);
          txt.setSelected(false);
          excel.setSelected(false);
        }
      }
    });
    ppt.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if(all.isSelected()){
          ppt.setSelected(true);
          all.setSelected(false);
          pdf.setSelected(false);
          word.setSelected(false);
          txt.setSelected(false);
          excel.setSelected(false);
        }
      }
    });
    excel.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if(all.isSelected()){
          excel.setSelected(true);
          all.setSelected(false);
          pdf.setSelected(false);
          ppt.setSelected(false);
          txt.setSelected(false);
          word.setSelected(false);
        }
      }
    });
    pdf.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if(all.isSelected()){
          pdf.setSelected(true);
          all.setSelected(false);
          word.setSelected(false);
          ppt.setSelected(false);
          txt.setSelected(false);
          excel.setSelected(false);
        }
      }
    });
  }
  private void setTextContentSelectionBoxEvent(){
    //����һ�����ѡ��ȫ���ı�,��������
    page.addFocusListener(new FocusListener() {
      @Override
      public void focusGained(FocusEvent e) {
        page.setSelectionStart(0);
        page.setSelectionEnd(page.getText().length());
      }
      @Override
      public void focusLost(FocusEvent e) {
      }
    });
    TextContent.addFocusListener(new FocusListener() {
      @Override
      public void focusGained(FocusEvent e) {
        int selectIndex=highLightIndex.get(nowIndex-1);
        TextContent.setCaretPosition(selectIndex);
        TextContent.setSelectionStart(selectIndex);
        TextContent.setSelectionEnd(selectIndex+highLightLenth.get(selectIndex));
      }

      @Override
      public void focusLost(FocusEvent e) {

      }
    });
    //���ð�ť������
    page.addActionListener(e->{
      String pageText=page.getText();
      if(pageText.contains("/")){
        pageText=pageText.substring(0,pageText.indexOf("/"));
      }
      int pageIndex = nowIndex;
      try {
        pageIndex=Integer.valueOf(pageText).intValue();
        if(pageIndex==0){
          pageIndex=1;
        }
      }catch (NumberFormatException numberFormatException){
        numberFormatException.printStackTrace();
        JOptionPane.showMessageDialog(f, "��������ȷ�ĸ�ʽ", "����",
                JOptionPane.ERROR_MESSAGE);
        return;
      }finally {
        if(pageIndex>highLightIndex.size()){
          pageIndex=highLightIndex.size();
        }
        nowIndex=pageIndex;
        page.setText(nowIndex+"/"+highLightIndex.size());
      }
      page.setSelectionStart(0);
      page.setSelectionEnd(page.getText().length());
      int selectIndex=highLightIndex.get(pageIndex-1);
      TextContent.setCaretPosition(selectIndex);
      TextContent.setSelectionStart(selectIndex);
      TextContent.setSelectionEnd(selectIndex+highLightLenth.get(selectIndex));
    });
    //���ϲ���
    up.addActionListener((e)->{
      if(nowIndex<=1){
        return;
      }
      nowIndex--;
      page.setText(nowIndex+"/"+highLightIndex.size());
      int selectIndex=highLightIndex.get(nowIndex-1);
      TextContent.setCaretPosition(selectIndex);
      TextContent.setSelectionStart(selectIndex);
      TextContent.setSelectionEnd(selectIndex+highLightLenth.get(selectIndex));
    });
    //���²���
    down.addActionListener((e)->{
      if(nowIndex>=highLightIndex.size()){
        return;
      }
      nowIndex++;
      page.setText(nowIndex+"/"+highLightIndex.size());
      int selectIndex=highLightIndex.get(nowIndex-1);
      TextContent.setCaretPosition(selectIndex);
      TextContent.setSelectionStart(selectIndex);
      TextContent.setSelectionEnd(selectIndex+highLightLenth.get(selectIndex));
    });
    highLight.addActionListener(e -> {
      if(isHighlighted){
        //�Ƴ�����
        TextLighter.removeAllHighlights();
        isHighlighted=false;
      }
      else {
        //��Ӹ���
        setHighlight(keyWord);
        isHighlighted=true;
      }
      int selectIndex=highLightIndex.get(nowIndex-1);
      TextContent.setSelectionStart(selectIndex);
      TextContent.setSelectionEnd(selectIndex+highLightLenth.get(selectIndex));
    });
    colorChoose.addActionListener(e->{
      DefaultColorSelectionModel Model=new DefaultColorSelectionModel();
      JColorChooser jColorChooser=new JColorChooser(Model);
      Color color=jColorChooser.getColor();
      Color color1=JColorChooser.showDialog(null,"��ɫѡ��",color);
      p = new DefaultHighlighter.DefaultHighlightPainter(color1);
      TextLighter.removeAllHighlights();
      setHighlight(keyWord);
      isHighlighted=true;
    });
  }
  private boolean isSelected(String path){
    if(path.endsWith(".txt")) {
      return txt.isSelected();
    }
    else if(path.endsWith(".doc")||path.endsWith(".docx")){
      return word.isSelected();
    }
    else if(path.endsWith(".pdf")){
      return pdf.isSelected();
    }
    else if(path.endsWith(".pptx")||path.endsWith(".ppt")){
      return ppt.isSelected();
    }
    else if(path.endsWith(".xlsx")||path.endsWith(".xls")){
      return excel.isSelected();
    }
    else {
      return true;
    }
  }
  static String getFileName(String path){
    if(path.contains("/")) {
      return path.substring(path.lastIndexOf("/")+1);
    }
    else if(path.contains("\\")) {
      return path.substring(path.lastIndexOf("\\")+1);
    }
    return path;
  }
  public static void main(String[] args) {
    mainUI mainUI=new mainUI();
    mainUI.init();
  }

  public class JProgressBarDemo extends JFrame {
    private void centerWindow(){
      //�����ʾ�����洰�ڵĴ�С
      Toolkit tk=getToolkit();
      Dimension dm=tk.getScreenSize();
      //�ô��ھ�����ʾ
      setLocation((int)(dm.getWidth()-getWidth())/2,(int)(dm.getHeight()-getHeight())/2);
    }
    public JProgressBarDemo(ArrayList<String> arrayList) {
      setTitle("�½�����");
      JLabel label=new JLabel("�½�����");
      //����һ��������
      JProgressBar progressBar=new JProgressBar();
      JButton button=new JButton("���");
      button.setEnabled(false);
      Container container=getContentPane();
      container.setLayout(new GridLayout(3,1));
      JPanel panel1=new JPanel(new FlowLayout(FlowLayout.LEFT));
      JPanel panel2=new JPanel(new FlowLayout(FlowLayout.CENTER));
      JPanel panel3=new JPanel(new FlowLayout(FlowLayout.RIGHT));
      panel1.add(label);    //��ӱ�ǩ
      panel2.add(progressBar);    //��ӽ�����
      panel3.add(button);    //��Ӱ�ť
      container.add(panel1);
      container.add(panel2);
      container.add(panel3);
      this.setSize(100,100);
      centerWindow();
      progressBar.setStringPainted(true);
      //����һ���̴߳������
      new Progress(progressBar, button,arrayList).start();
      button.addActionListener(new ActionListener()
      {
        @Override
        public void actionPerformed(ActionEvent e)
        {
          dispose();
        }
      });
    }
    private class Progress extends Thread {
      JProgressBar progressBar;
      JButton button;
      ArrayList<String> arrayList;
      Progress(JProgressBar progressBar,JButton button,ArrayList<String> _arrayList)
      {
        this.progressBar=progressBar;
        this.button=button;
        this.arrayList=_arrayList;
      }

      @Override
      public void run() {
        try {
          //����ѡ���ļ��Ĵ���
          int fileSize=arrayList.size();
          String text;
          int index=1;
          for(String string:arrayList){
            text=getText.read(string);
            fI.addData(string,text);
            System.out.println(string);
            progressBar.setValue((index++)*100/fileSize);
            //progressBar.setString((index++)+"/"+fileSize);
          }
          fI.fileClose();
        } catch (IOException ioException) {
          ioException.printStackTrace();
        }
        progressBar.setIndeterminate(false);
        progressBar.setString("������ɣ�");
        button.setEnabled(true);
      }
    }
  }
}


