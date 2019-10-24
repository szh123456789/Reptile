import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;

    public class pa{
        public static void main(String[] args) throws IOException {
            // TODO Auto-generated method stub
             List<String> list= getEmailFromWeb();
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
            List<String> list = new ArrayList<>();
            List<String> ll=new ArrayList<>();
            List<String> lll= new ArrayList<>();
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

                        ll.add(aa[2]);
                        //2层爬虫
            URL url= new URL("https://www.fancai.com/meishi/"+ll.get(j)+"/");
            BufferedReader bufferedReader= new BufferedReader(new InputStreamReader(url.openStream()));
            String re=
                    "<td><p>(\\W*|\\W*+\\d+\\W*|\\W*+\\d+\\W*+\\d*-\\d*+\\W*|\\W*+\\d-\\d+\\W*)</p></td>";
            Pattern pattern= Pattern.compile(re);
            String lline = null;

            while ((lline= bufferedReader.readLine())!=null) {
                Matcher matcher= pattern.matcher(lline);
                while (matcher.find()) {
                    lll.add(matcher.group());



                }
            }
                        j++;
                    }
                }
                i++;
            }
           try{
               int k=0;
               while(k<lll.size()) {
                   bw.write(lll.get(k) + "\r\n");
                  k++;
               }
           }catch (Exception e){
        e.printStackTrace();
          }
            try {
                bw.flush();
                write.close();
                bw.close();
                write.close();

            }catch (Exception e){
                e.printStackTrace();
            }
    return lll;
        }

    }


