import java.io.*;
import java.net.*;

class TP01Q08HTML {
   public static String getHtml(String endereco) {
      URL url;
      InputStream is = null;
      BufferedReader br;
      String resp = "", line;

      try {
         url = new URL(endereco);
         is = url.openStream(); // throws an IOException
         br = new BufferedReader(new InputStreamReader(is));

         while ((line = br.readLine()) != null) {
            resp += line + "\n";
         }
      } catch (MalformedURLException mue) {
         mue.printStackTrace();
      } catch (IOException ioe) {
         ioe.printStackTrace();
      }

      try {
         is.close();
      } catch (IOException ioe) {
         // nothing to see here

      }

      return resp;
   }

   static boolean isFim(String str) {
      boolean resp = false;
     
         if (str.length() == 3 && str.charAt(0) == 'F' && str.charAt(1) == 'I' && str.charAt(2) == 'M') {
            resp = true;
         }

      return resp;
   }

   public static void main(String[] args) {
      String endereco, html, nome;
      String fim = "";
      // nome = MyIO.readLine();
      /*
       * endereco = MyIO.readLine(); html = getHtml(endereco); showVogais(html, nome);
       */
      do {
      nome = MyIO.readLine();
      fim = nome;
      if(!isFim(nome))
      {
         endereco = MyIO.readLine();
         html = getHtml(endereco);
         showVogais(html, nome); 
      }
      } while (!isFim(nome)); 
   }
   public static void showVogais(String str, String nome) {
      int a = 0, e = 0, i = 0, o = 0, u = 0, aa = 0, ee = 0;
      int ii = 0, oo = 0, uu = 0, aaa = 0, eee = 0, iii = 0, ooo = 0, uuu = 0, ac = 0, oc = 0, aaaa = 0, eeee = 0,
            iiii = 0, uuuu = 0, oooo = 0, consoante = 0, br = 0, table = 0;
      for (int p = 0; p < str.length(); p++) {
         if (str.charAt(p) == 'a')
            a++;
         else if (str.charAt(p) == 'e')
            e++;
         else if (str.charAt(p) == 'i')
            i++;
         else if (str.charAt(p) == 'o')
            o++;
         else if (str.charAt(p) == 'u')
            u++;
         else if (str.charAt(p) == (char) 0x00E1)
            aa++;
         else if (str.charAt(p) == (char) 0x00E9)
            ee++;
         else if (str.charAt(p) == (char) 0x00ED)
            ii++;
         else if (str.charAt(p) == (char) 0x00F3)
            oo++;
         else if (str.charAt(p) == (char) 0x00FA)
            uu++;
         else if (str.charAt(p) == (char) 0x00E0)
            aaa++;
         else if (str.charAt(p) == (char) 0x00E8)
            eee++;
         else if (str.charAt(p) == (char) 0x00EC)
            iii++;
         else if (str.charAt(p) == (char) 0x00F2)
            ooo++;
         else if (str.charAt(p) == (char) 0x00F9)
            uuu++;
         else if (str.charAt(p) == (char) 0x00E3)
            ac++;
         else if (str.charAt(p) == (char) 0x00F5)
            oc++;
         else if (str.charAt(p) == (char) 0x800E2)
            aaaa++;
         else if (str.charAt(p) == (char) 0x00EA)
            eeee++;
         else if (str.charAt(p) == (char) 0x00EE)
            iiii++;
         else if (str.charAt(p) == (char) 0x00F4)
            oooo++;
         else if (str.charAt(p) == (char) 0x00FB)
            uuuu++;
         else if (str.charAt(p) >= 'a'&& str.charAt(p) <= 'z')
            consoante++;
         else if (str.charAt(p) == '<' && str.charAt(p + 1) == 'b' && str.charAt(p + 2) == 'r'
               && str.charAt(p + 3) == '>')
            br++;
         else if (str.charAt(p) == '<' && str.charAt(p + 1) == 't' && str.charAt(p + 2) == 'a'
               && str.charAt(p + 3) == 'b' && str.charAt(p + 6) == '>')
            table++;
      }
      a = a-table;
      e = e-table;
      System.out.println("a(" + a + ") " + " e(" + e + ")" + " i(" + i + ") " + " o(" + o + ") " + " u(" + u + ") "
            + (char) 0x00E1 + "(" + aa + ") " + (char) 0x00E9 + "(" + ee + ") " + (char) 0x00ED + "(" + ii + ") "
            + (char) 0x00F3 + "(" + oo + ") " + (char) 0x00FA + "(" + uu + ") " + (char) 0x00E0 + "(" + aaa + ") "
            + (char) 0x00E8 + "(" + eee + ") " + (char) 0x00EC + "(" + iii + ") " + (char) 0x00F2 + "(" + ooo + ") "
            + (char) 0x00F9 + "(" + uuu + ") " + (char) 0x00E3 + "(" + ac + ") " + (char) 0x00F5 + "(" + oc + ") "
            + (char) 0x800E2 + "(" + aaaa + ") " + (char) 0x00EA + "(" + eeee + ") " + (char) 0x00EE + "(" + iiii + ") "
            + (char) 0x00F4 + "(" + oooo + ") " + (char) 0x00FB + "(" + uuuu + ") " + " consoante(" + consoante + ") "
            + " <br>(" + br + ") " + " <table>(" + table + ") " + nome);
   }

}