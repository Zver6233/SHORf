package SHLK;

public class URL {

    public static URL_MANIP ManipulationOfDB=new URL_MANIP(); //создаем обєкт для проврки дубликаторов которотких ссилок
    private String Long_URL="";//эта переменная хранить длинную ссылку ведденую пользователем

    public URL(String s)

    {
        String www="https";

        int c=0;

        for (int i=0;i<www.length();i++)
            if(s.charAt(i)==www.charAt(i))
                c++;
        System.out.println(c);
        if (c<4)
            s="http://"+s;


        this.Long_URL=s;
    }// конструктор



    public String doShort()
    {
        ManipulationOfDB.coppy();
        String chari="0123456789_qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM";
        String Tiny_URL="";
        int lengthofshorturl = 5; //длина короткой ссылки
        int countofmatchs = 0;

        if(ManipulationOfDB.ifExistLongURL(Long_URL)) //Если такой юрл уже использовался тогда вернуть уже имеющее значения
            return URL_MANIP.HereIsShortAndLongLinks.get(Long_URL);
        //Генерировать юрл до тех пор пока оно не будет уникальным
        while (true) {
            for (int i = 0; i < lengthofshorturl; i++)
                Tiny_URL = Tiny_URL+ chari.charAt((int) (Math.random() * chari.length() + 0));

            if (ManipulationOfDB.IfLinkIsOriginalPutIt(Long_URL,Tiny_URL)) //проверка на наличия
                break;
            countofmatchs++; // в противном случаи добавить счетчик попыток +1

            //Если не удалось сгенерировать уникальный короткий юрл 5 раз подряд, значит добавить к короткому юрл +1 символ
            if (countofmatchs == 5)
                lengthofshorturl++;
        }
        return Tiny_URL;
    }




}
