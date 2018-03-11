

package foreinsickit;
private void button2_Click(object sender, EventArgs e)
{
    int c = sb.Length,x,y,z=0;
    char[] Message= new char[c];
    StringBuilder RetreivedMessage = new StringBuilder();
    for (x = 0, y = 0; x < image1.Width && z < c; x++)
    {
        for (y = 0; y < image1.Height && z < c; y++)
        {
            Color pixelColor = image1.GetPixel(x, y);
            string binary1 = Convert.ToString(pixelColor.R, 2);
            //byte NewRed, NewGreen, NewBlue;
            if (binary1[binary1.Length -1] == '0')
            {
                RetreivedMessage.Append('0');
                z++;
                if (z == c)
                {
                    break;
                }

            }
            else
            {
                RetreivedMessage.Append('1');
                z++;
                if (z == c)
                {
                    break;++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
                    +
                }

            }
            binary1 = Convert.ToString(pixelColor.G, 2);
            //byte NewRed, NewGreen, NewBlue;
            if (binary1[binary1.Length - 1] == '0')
            {
                RetreivedMessage.Append('0');
                z++;
                if (z == c)
                {
                    break;
                }

            }
            else
            {
                RetreivedMessage.Append('1');
                z++;
                if (z == c)
                {
                    break;
                }

            }
            binary1 = Convert.ToString(pixelColor.B, 2);
            //byte NewRed, NewGreen, NewBlue;
            if (binary1[binary1.Length - 1] == '0')
            {
                RetreivedMessage.Append('0');
                z++;
                if (z == c)
                {
                    break;
                }

            }
            else
            {
                RetreivedMessage.Append('1');
                z++;
                if (z == c)
                {
                    break;
                }

            }
            //string binary1 = Convert.ToString(pixelColor.R, 2);
            //char last1 = binary1[binary1.Length - 1];
        }
    }
    string FinalRetreivedMessage  = RetreivedMessage.ToString();
    //Byte[] buf = Encoding.Unicode.GetBytes(RetreivedMessage.ToString());
    Byte[] buf = Encoding.Unicode.GetBytes(RetreivedMessage.ToString());
    string result = System.Text.Encoding.Unicode.GetString(buf);
    //String result = Encoding.Unicode.GetString(buf);
    StringBuilder r2 = new StringBuilder();
    //foreach (Byte b in Encoding.Unicode.GetBytes(FinalRetreivedMessage))
   // {
   //    r2.Append(Convert.ToString());
    //}
}

public class Main {

    public static void main(String[] args) {
        // TODO code application logic here
    }

}
