import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class tu {
    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        List<String> list = getEmailFromWeb();
        for (String string : list) {
            System.out.println(string);

        }

    }


    public static List<String> getEmailFromWeb() throws IOException{
        String path="/home/szh/桌面/paa";

        Writer write=new FileWriter(path);
        BufferedWriter bw=new BufferedWriter(write);
        int i=1;
        int j=0;
        int o=0;
        int g=0;
        List<String> list = new ArrayList<>();
        List<String> ll=new ArrayList<>();
        List<String> lll= new ArrayList<>();
        List<String> llll=new ArrayList<>();
        List<String> lllll=new ArrayList<>();

        HashMap<String,String> hh=new HashMap<>();
        //1层爬虫

        while (i<101) {
            URL u = new URL("https://www.fancai.com/meishi_"+i+"/");
            BufferedReader br = new BufferedReader(new InputStreamReader(u.openStream()));


            String w = "<a href=\"/\\w*/\\d*/\" target=\"\\w*\">\\W*</a>";
            Pattern p = Pattern.compile(w);
            String line = null;

            while ((line = br.readLine()) != null) {
                Matcher ma = p.matcher(line);
                while (ma.find()) {


                    list.add(ma.group());
                    String s=list.get(j);
                    String[] aa=s.split("/");
                    String[] aaa=aa[3].split(">");
                    String[] aaaa=aaa[1].split("<");
                    llll.add(aaaa[0]); //菜名
                    ll.add(aa[2]); //二段连接
//                    Random r=new Random();
//                    int rr=r.nextInt(30-10)+10;
                    try {
//                        Thread.sleep(rr * 1000);
                        //2层爬虫
                        URL url = new URL("https://www.fancai.com/meishi/" + ll.get(j) + "/");
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
                        String re =
                                "<td><p>(\\W*|\\W*+\\w*+\\W*|\\W*+\\w*+\\W*+\\w*-\\w*+\\W*|\\W*+\\w*-\\w*+\\W*)</p></td>";
                        Pattern pattern = Pattern.compile(re);
                        String lline = null;
                        System.out.println(llll.get(j));
                        bw.write(llll.get(j) + "\r\n");
                        while ((lline = bufferedReader.readLine()) != null) {
                            Matcher matcher = pattern.matcher(lline);
                            while (matcher.find()) {
                                lll.add(matcher.group()); //带标点的步骤

                                String b = lll.get(o);
                                String[] bb = b.split("<td><p>");
                                String[] bbb = bb[1].split("</p></td>");


                                try {
                                    if(bbb.length!=0){

                                        lllll.add(bbb[0]); //步骤
                                        System.out.println(lllll.get(g));
                                        bw.write(lllll.get(g) + "\r\n");
                                        g++;
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }


                                o++;
                            }
                        }

                    }catch (Exception e){
                        e.printStackTrace();
                    }
//                    System.out.println(j);
                    j++;
                }
            }
            i++;


            }

        try {
            bw.flush();
            write.close();
            bw.close();
            write.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return llll;
    }

}
