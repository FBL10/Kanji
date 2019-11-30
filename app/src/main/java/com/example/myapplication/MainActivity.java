package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int[] ordre = new int[0];


    String[][] mots = {{"人","ひと","hito","personne"},
            {"男","おとこ","otoko","homme"},
            {"女","おんな","onna","femme"},
            {"子","こ","ko","enfant"},
            {"日","ひ","hi","soleil"},
            {"月","つき","tsuki","lune"},
            {"時","とき","tori","temps"},
            {"水","みず","mizu","eau"},
            {"火","ひ","hi","feu"},
            {"土","つち","tsuchi","terre"},
            {"風","かぜ","kaze","vent"},
            {"空","そら","sora","ciel"},
            {"山","ゃま","yama","montagne"},
            {"川","かわ","kawa","rivière"},
            {"木","き","ki","arbre"},
            {"花","はな","hana","fleur"},
            {"雨","あめ","ame","pluie"},
            {"雪","ゅき","yuki","neige"},
            {"金","かね","kane","argent"},
            {"刀","かたな","katana","sabre"},
            {"はい","","hai","oui"},
            {"いいえ","","īe","non"},
            {"お願いします","おねがいします","onegaishimasu","s'il vous plait"},
            {"(どうも)ありがとう(ございます)","","(dōmo) arigatō (gozaimasu)","merci (beaucoup)"},
            {"どういたしまして","","dōitashimashite","de rien"},
            {"おはよう(ございます)","","ohayō (gozaimasu)","bon matin"},
            {"こんにちは","","konnichiwa","bonjour"},
            {"こんばんは","","konbanwa","bonsoir"},
            {"お元気ですか","おゲンキですか","ogenkidesuka","Comment ça va?"},
            {"さようなら","","sayōnara","au revoir"},
            {"またね","","matane","à plus tard"},
            {"おやすみ(なさい)","","oyasumi(nasai)","bonne nuit"},
            {"また明日","またあした","mata ashita","à demain"},
            {"初めまして","はじめまして","hajimemashite","enchanté"},
            {"(どうぞ)宜しく(お願いします)","(どうぞ)よろしく(おねがいします)","(dōzo)yoroshiku(onegaishimasu)","heureux de vous rencontrer"},
            {"いただきます","","itadakimasu","merci pour le repas"},
            {"すみません","","sumimasen","excusez-moi"},
            {"ごめん(なさい)","","gomen (nasai)","désolé"},
            {"ようこそ","","yōkoso","bienvenue"},
            {"お邪魔します","おジャマします","ojamashimasu","je rentre!"},
            {"ベッド","","beddo","lit"},
            {"ソファー","","sofā","canapé"},
            {"(ボール)ペン","","(bōru)pen","stylo(-bille)"},
            {"ノート","","nōto","cahier"},
            {"カレンダー","","karendā","calendrier"},
            {"ラジオ","","rajio","radio"},
            {"ステレオ","","sutereo","système de son"},
            {"テレビ","","terebi","télévision"},
            {"カメラ","","kamera","appareil photo"},
            {"コンピュータ","","konpyūta","ordinateur"},
            {"エアコン","","eakon","climatiseur"},
            {"リモコン","","rimokon","télécommande"},
            {"クレジットカード","","kurejittokādo","carte de crédit"},
            {"ドア","","doa","porte"},
            {"ランプ","","ranpu","lampe"},
            {"テーブル","","tēburu","table"},
            {"フォーク","","fōku","fourchette"},
            {"ナイフ","","naifu","couteau"},
            {"スプーン","","supūn","cuillère"},
            {"トイレ","","toire","toilettes"},
            {"犬","いぬ","inu","chien"},
            {"猫","ねこ","neko","chat"},
            {"鳥","とり","tori","oiseau"},
            {"魚","さかな","sakana","poisson"},
            {"狸","たぬき","tanuki","tanuki"},
            {"兎","うさぎ","usagi","lapin"},
            {"牛","うし","ushi","vache"},
            {"羊","ひつじ","hitsuji","mouton"},
            {"山羊","ゃぎ","yagi","chèvre"},
            {"豚","ぶた","buta","cochon"},
            {"馬","うま","uma","cheval"},
            {"鹿","しか","shika","cerf"},
            {"ロバ","","roba","âne"},
            {"ラクダ","","rakuda","dromadaire/chameau"},
            {"鶏","にわとり","niwatori","poule"},
            {"鼠","ねずみ","nezumi","souris",},
            {"リス","","risu","écureuil",},
            {"針鼠","はりねずみ","harinezumi","hérisson"},
            {"ハムスター","","hamusutā","hamster"},
            {"フェレット","","feretto","furet"},
            {"夫","おっと","otto","mon époux"},
            {"妻","つま","tsuma","mon épouse"},
            {"両親","リョウシン","ryōshin","mon parent"},
            {"父","ちち","chichi","mon père"},
            {"母","はは","haha","ma mère"},
            {"子供","こども","kodomo","mon enfant"},
            {"息子","むすこ","musuko","mon fils"},
            {"娘","むすめ","musume","ma fille"},
            {"祖父母","ソフボ","sofubo","mon grand-parent"},
            {"祖父","ソフ","sofu","mon grand-père"},
            {"祖母","ソボ","sobo","ma grand-mère"},
            {"孫","まご","mago","mon petit-enfant"},
            {"双子","ふたご","futago","mon/ma jumeau/jumelle"},
            {"兄","あに","ani","mon frère ainé"},
            {"弟","おとうと","otōto","mon frère cadet"},
            {"姉","あね","ane","ma soeur ainée"},
            {"妹","いもうと","imōto","ma soeur cadette"},
            {"兄弟","キョウダイ","kyōdai","fratrie"},
            {"従兄","いとこ","itoko","mon cousin ainé"},
            {"従弟","いとこ","itoko","mon cousin cadet"},
            {"従姉","いとこ","itoko","ma cousine ainée"},
            {"従妹","いとこ","itoko","ma cousine cadette"},
            {"伯父","おじ","oji","mon oncle ainé"},
            {"叔父","おじ","oji","mon oncle cadet"},
            {"伯母","おば","oba","ma tante ainée"},
            {"叔母","おば","oba","ma tante cadette"},
            {"義兄","ギケイ","gikē","mon beau-frère ainé"},
            {"義弟","ギテイ","gitē","mon beau-frère cadet"},
            {"義姉","ギし","gishi","ma belle-soeur ainée"},
            {"義妹","ギマイ","gimai","ma belle-soeur cadette"},
            {"赤","あか","aka","rouge"},
            {"青","あお","ao","bleu"},
            {"黄色","きいろ","kiiro","jaune"},
            {"オレンジ色","オレンジいろ","orenjiiro","orange"},
            {"紫","むらさき","murasaki","mauve"},
            {"緑","みどり","midori","vert"},
            {"白","しろ","shiro","blanc"},
            {"黒","くろ","kuro","noir"},
            {"灰色","はいいろ","haiiro","gris"},
            {"ピンク","","pinku","rose"},
            {"茶色","チャいろ","chairo","brun"},
            {"ベージュ","","bēju","beige"},
            {"薄紫色","うすむらさきいろ","usumurasakiiro","mauve clair"},
            {"マゼンタ","","mazenta","magenta"},
            {"紅","くれない","kurenai","rouge foncé (cramoisi)"},
            {"シアン","","shian","cyan"},
            {"水色","みずいろ","mizuiro","bleu clair"},
            {"インディゴ","","indigo","indigo"},
            {"虹色","にじいろ","nijiiro","couleur arc-en-ciel"},
            {"金色","キンいろ","kin'iro","couleur or"},
            {"日曜日","にちようび","nichiyōbi","dimanche"},
            {"月曜日","ゲツヨウび","getsuyōbi","lundi"},
            {"火曜日","カヨウび","kayōbi","mardi"},
            {"水曜日","スイヨウび","suiyōbi","mercredi"},
            {"木曜日","モクヨウび","mokuyōbi","jeudi"},
            {"金曜日","キンヨウび","kinyōbi","vendredi"},
            {"土曜日","ドヨウび","doyōbi","samedi"},
            {"星","ほし","hoshi","astre"},
            {"地球","チキュウ","chikyū","la Terre"},
            {"太陽","タイヨウ","taiyō","le Soleil"},
            {"一","イチ","ichi","1"},
            {"二","ニ","ni","2"},
            {"三","サン","san","3"},
            {"四","よん","yon","4"},
            {"五","ゴ","go","5"},
            {"六","ロク","roku","6"},
            {"七","なな","nana","7"},
            {"八","ハチ","hachi","8"},
            {"九","キュウ","kyū","9"},
            {"十","ジュウ","jū","10"},
            {"百","ヒャク","hyaku","100"},
            {"千","セン","sen","1000"},
            {"ゼロ","","zero","0"},
            {"三百","サンビャク","sanbyaku","300"},
            {"六百","ロッピャク","roppyaku","600"},
            {"八百","ハッピャク","happyaku","800"},
            {"三千","サンゼン","sanzen","3000"},
            {"八千","ハッセン","hassen","8000"},
            {"万","マン","man","10000"},
            {"億","オク","oku","100000000"},
            {"一日","ついたち","tsuitachi","le 1"},
            {"二日","ふつか","futsuka","le 2"},
            {"三日","みっか","mikka","le 3"},
            {"四日","よっか","yokka","le 4"},
            {"五日","いつか","itsuka","le 5"},
            {"六日","むいか","muika","le 6"},
            {"七日","なのか","nanoka","le 7"},
            {"八日","ようか","yōka","le 8"},
            {"九日","ここのか","kokonoka","le 9"},
            {"十日","とおか","tōka","le 10"},
            {"十四日","ジュウよっか","jūyokka","le 14"},
            {"二十日","はつか","hatsuka","le 20"},
            {"二十四日","ニジュウよっか","nijūyokka","le 24"},
            {"年","とし","toshi","année"},
            {"世紀","セイキ","sēki","siècle"}};

    public int[] make_shuffled_tab(int range) {

        int[] tab = new int[range];

        for (int i = 0; i < range; i++) {
            tab[i] = i;
        }

        shuffle(tab);

        return tab;
    }

    public static void shuffle(int[] tab){
        for (int i=0; i<tab.length; i++){
            int j = i + (int)(Math.random() * (((tab.length-1) - i) + 1));
            swap(tab, i, j);
        }
    }

    public static void swap(int[] tab, int i, int j){
        int temp = tab[i];
        tab[i] = tab[j];
        tab[j] = temp;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        button.getBackground().setAlpha(0);
    }

    int traduc=0;
    int romaji=0;
    Boolean skip = true;
    int i = 0;

    public void switch2(View v){
        traduc = 1-traduc;
        i=0;
        skip=true;
        buttonClick(v);
    }

    public void switch1(View v){
        TextView prononciation = (TextView)findViewById(R.id.prononciation);
        romaji= 1-romaji;
        if(ordre.length!=0 && skip) {
            if (mots[ordre[i-1]][1].length() == 0 && romaji==0){
                prononciation.setText("");
            } else {
                prononciation.setText("(" + mots[ordre[i-1]][1+romaji] + ")");
            }
        }

    }

    public void buttonClick(View v) {

        if (i == 0 && skip){
            ordre = make_shuffled_tab(mots.length);
        }

        TextView symbole = (TextView)findViewById(R.id.symbole);
        TextView prononciation = (TextView)findViewById(R.id.prononciation);
        TextView traduction = (TextView)findViewById(R.id.traduction);


        if (skip){
            symbole.setText(mots[ordre[i]][3*(traduc)]);
            symbole.setTextSize(30+(150/(symbole.getText()).length()));
            prononciation.setText("");
            traduction.setText("");
        }

        else{
            if (mots[ordre[i]][1+romaji].length() != 0) {
                prononciation.setText("("+mots[ordre[i]][1+romaji]+")");
            }
            traduction.setText(mots[ordre[i]][-3*traduc+3]);
            traduction.setTextSize(40+(100/(traduction.getText()).length()));
            i++;
        }

        if (i==mots.length){
            i = 0;
        }


        skip = !skip;
    }
}
