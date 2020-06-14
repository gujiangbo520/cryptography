# 密码学

## 第一章 密码学

### 1.1 密码学基本概念

密码在我们的生活中有着重要的作用，那么密码究竟来自何方，为何会产生呢？

密码学是网络安全、信息安全、区块链等产品的基础，常见的非对称加密、对称加密、散列函数等，都属于密码学范畴。

密码学有数千年的历史，从最开始的替换法到如今的非对称加密算法，经历了古典密码学，近代密码学和现代密码学三个阶段。密码学不仅仅是数学家们的智慧，更是如今网络空间安全的重要基础。

#### 1.1.1 古典密码学

在古代的战争中，多见使用隐藏信息的方式保护重要的通信资料。比如先把需要保护的信息用化学药水写到纸上，药水干后，纸上看不出任何的信息，需要使用另外的化学药水涂抹后才可以阅读纸上的信息。

`https://www.iqiyi.com/v_19rt6ab1hg.html` 1分05秒

这些方法都是在保护重要的信息不被他人获取，但藏信息的方式比较容易被他人识破，例如增加哨兵的排查力度，就会发现其中的猫腻，因而随后发展出了较难破解的古典密码学。

① 替换法

替换法很好理解，就是用固定的信息将原文替换成无法直接阅读的密文信息。例如将 `b` 替换成 `w` ，`e` 替换成`p` ，这样`bee` 单词就变换成了`wpp`，不知道替换规则的人就无法阅读出原文的含义。

替换法有单表替换和多表替换两种形式。单表替换即只有一张原文密文对照表单，发送者和接收者用这张表单来加密解密。在上述例子中，表单即为：`a b c d e - s w t r p` 。

多表替换即有多张原文密文对照表单，不同字母可以用不同表单的内容替换。

例如约定好表单为：表单 `1：abcde-swtrp` 、表单`2：abcde-chfhk` 、表单 `3：abcde-jftou`。

规定第一个字母用第三张表单，第二个字母用第一张表单，第三个字母用第二张表单，这时 `bee`单词就变成了

`(312)fpk` ，破解难度更高，其中 312 又叫做密钥，密钥可以事先约定好，也可以在传输过程中标记出来。

② 移位法

移位法就是将原文中的所有字母都在字母表上向后（或向前）按照一个固定数目进行偏移后得出密文，典型的移位法应用有 “ 恺撒密码 ”。

例如约定好向后移动2位`（abcde - cdefg）`，这样 `bee` 单词就变换成了`dgg` 。

同理替换法，移位法也可以采用多表移位的方式，典型的多表案例是“维尼吉亚密码”（又译维热纳尔密码），属于多表密码的一种形式。

![mult](https://github.com/gujiangbo520/cryptography/blob/master/src/main/resources/images/mult.png?raw=true)

③ 古典密码破解方式

古典密码虽然很简单，但是在密码史上是使用的最久的加密方式，直到“概率论”的数学方法被发现，古典密码就被破解了。

![image2](https://github.com/gujiangbo520/cryptography/blob/master/src/main/resources/images/frequency%20analysis.png?raw=true)

英文单词中字母出现的频率是不同的，e以12.702%的百分比占比最高，z 只占到0.074%，感兴趣的可以去百科查字母频率详细统计数据。如果密文数量足够大，仅仅采用频度分析法就可以破解单表的替换法或移位法。

![image3](https://github.com/gujiangbo520/cryptography/blob/master/src/main/resources/images/synopsis.png?raw=true)

多表的替换法或移位法虽然难度高一些，但如果数据量足够大的话，也是可以破解的。以维尼吉亚密码算法为例，破解方法就是先找出密文中完全相同的字母串，猜测密钥长度，得到密钥长度后再把同组的密文放在一起，使用频率分析法破解。

#### 1.1.2 近代密码学

古典密码的安全性受到了威胁，外加使用便利性较低，到了工业化时代，近现代密码被广泛应用。

**恩尼格玛机**

​	恩尼格玛机是二战时期纳粹德国使用的加密机器，后被英国破译，参与破译的人员有被称为计算机科学之父、人工智能之父的图灵。

<img src="https://github.com/gujiangbo520/cryptography/blob/master/src/main/resources/images/coder.png?raw=true" alt="image4" style="zoom: 50%;" />

恩尼格玛机

恩尼格玛机使用的加密方式本质上还是移位和替代，只不过因为密码表种类极多，破解难度高，同时加密解密机器化，使用便捷，因而在二战时期得以使用。

#### 1.1.3 现代密码学

① 散列函数

散列函数，也见杂凑函数、摘要函数或哈希函数，可将任意长度的消息经过运算，变成固定长度数值，常见的有`MD5`、`SHA-1`、`SHA256`，多应用在文件校验，数字签名中。

MD5 可以将任意长度的原文生成一个128位（16字节）的哈希值

SHA-1可以将任意长度的原文生成一个160位（20字节）的哈希值

② 对称密码

对称密码应用了相同的加密密钥和解密密钥。对称密码分为：序列密码(流密码)，分组密码(块密码)两种。流密码是对信息流中的每一个元素（一个字母或一个比特）作为基本的处理单元进行加密，块密码是先对信息流分块，再对每一块分别加密。

例如原文为1234567890，流加密即先对1进行加密，再对2进行加密，再对3进行加密……最后拼接成密文；块加密先分成不同的块，如1234成块，5678成块，90XX(XX为补位数字)成块，再分别对不同块进行加密，最后拼接成密文。前文提到的古典密码学加密方法，都属于流加密。

③ 非对称密码

对称密码的密钥安全极其重要，加密者和解密者需要提前协商密钥，并各自确保密钥的安全性，一但密钥泄露，即使算法是安全的也无法保障原文信息的私密性。

在实际的使用中，远程的提前协商密钥不容易实现，即使协商好，在远程传输过程中也容易被他人获取，因此非对称密钥此时就凸显出了优势。

非对称密码有两支密钥，公钥（publickey）和私钥（privatekey），加密和解密运算使用的密钥不同。用公钥对原文进行加密后，需要由私钥进行解密；用私钥对原文进行加密后（此时一般称为签名），需要由公钥进行解密（此时一般称为验签）。公钥可以公开的，大家使用公钥对信息进行加密，再发送给私钥的持有者，私钥持有者使用私钥对信息进行解密，获得信息原文。因为私钥只有单一人持有，因此不用担心被他人解密获取信息原文。

#### 1.1.4 如何设置密码才安全

- 密码不要太常见，不要使用类似于123456式的常用密码。
- 各应用软件密码建议不同，避免出现一个应用数据库被脱库，全部应用密码崩塌，
- 可在设置密码时增加注册时间、注册地点、应用特性等方法。例如tianjin123456，表示在天津注册的该应用。

### 1.2 ASCII编码

ASCII（American Standard Code for Information Interchange，美国信息交换标准代码）是基于拉丁字母的一套电脑编码系统，主要用于显示现代英语和其他西欧语言。它是现今最通用的单字节编码系统，并等同于国际标准ISO/IEC 646。

<img src="https://github.com/gujiangbo520/cryptography/blob/master/src/main/resources/images/ascii.png?raw=true" alt="image5" style="zoom:67%;" />

**ascii在Java代码中使用**

字符转换成ascill

```java
/**
 * Ascii 使用
 * @author gujiangbo
 */
public class AsciiTest {
    public static void main(String[] args) {
        char ch = 'a';int cha = ch;
        char zh = '中';int zha = zh;
        System.out.println(cha);
        System.out.println(zha);
    }
}
```

输出结果：

```
97
20013
```

字符串转换成ascill

```java
/**
 * 字符串转换成Ascii
 */
public class AscillTest02 {
    public static void main(String[] args) {
        String msg = "gujiangbo";
        char[] chars = msg.toCharArray();
        for (char c : chars) {
            int ascill = c;
            System.out.println(ascill);
        }
    }
}
```

输出结果：

```
103
117
106
105
97
110
103
98
111
```

### 1.3 恺撒加密

#### 1.3.1 中国古代加密

看一个小故事 ， 看看古人如何加密和解密：

公元683年，唐中宗即位。随后，武则天废唐中宗，立第四子李旦为皇帝，但朝政大事均由她自己专断。　　

　　裴炎、徐敬业和骆宾王等人对此非常不满。徐敬业聚兵十万，在江苏扬州起兵。裴炎做内应，欲以拆字手段为其传递秘密信息。后因有人告密，裴炎被捕，未发出的密信落到武则天手中。这封密信上只有“青鹅”二字，群臣对此大惑不解。　　

　　武则天破解了“青鹅”的秘密：“青”字拆开来就是“十二月”，而“鹅”字拆开来就是“我自与”。密信的意思是让徐敬业、骆宾王等率兵于十二月进发，裴炎在内部接应。“青鹅”破译后，裴炎被杀。接着，武则天派兵击败了徐敬业和骆宾王。

<img src="https://github.com/gujiangbo520/cryptography/blob/master/src/main/resources/images/qinge.png?raw=true" alt="iamge7" style="zoom: 50%;" />

#### 1.3.2 外国加密

在密码学中，恺撒密码是一种最简单且最广为人知的加密技术。

凯撒密码最早由古罗马军事统帅盖乌斯·尤利乌斯·凯撒在军队中用来传递加密信息，故称凯撒密码。这是一种位移加密方式，只对26个字母进行位移替换加密，规则简单，容易破解。下面是位移1次的对比：

![image8](https://github.com/gujiangbo520/cryptography/blob/master/src/main/resources/images/mingmiwen.png?raw=true)

将明文字母表向后移动1位，A变成了B，B变成了C……，Z变成了A。同理，若将明文字母表向后移动3位：

![image9](https://github.com/gujiangbo520/cryptography/blob/master/src/main/resources/images/mingwenmiwen.png?raw=true)

则A变成了D，B变成了E……，Z变成了C。

字母表最多可以移动25位。凯撒密码的明文字母表向后或向前移动都是可以的，通常表述为向后移动，如果要向前移动1位，则等同于向后移动25位，位移选择为25即可。

它是一种替换加密的技术，明文中的所有字母都在字母表上向后（或向前）按照一个固定数目进行偏移后被替换成密文。

例如，当偏移量是3的时候，所有的字母A将被替换成D，B变成E，以此类推。

这个加密方法是以恺撒的名字命名的，当年恺撒曾用此方法与其将军们进行联系。 

恺撒密码通常被作为其他更复杂的加密方法中的一个步骤。

![image10](https://github.com/gujiangbo520/cryptography/blob/master/src/main/resources/images/duizhaob.png?raw=true)

**简单来说就是当秘钥为n，其中一个待加密字符ch，加密之后的字符为ch+n，当ch+n超过’z’时，回到’a’计数。**

#### 1.3.3 凯撒位移加密

创建类 `KaiserDemo`，把 `hello world` 往右边移动3位

```java
/**
 * 凯撒位移加密
 * @author gujiangbo
 */
public class KaiserDemo {

    public static void main(String[] args) {
        /**
         * 原始报文
         */
        String msg = "gujiangbo";
        /**
         * 位移量
         */
        int key = 3;
        String encrypt = encrypt(msg, key);
        System.out.println("密文:" + encrypt);
    }
     public static String encrypt(String msg, int key) {
        StringBuffer sb = new StringBuffer();
        if (StringUtils.isBlank(msg)) {
            return null;
        }
        for (char c : msg.toCharArray()) {
            int asciiCode = c;
            asciiCode = asciiCode + key;
            sb.append((char) asciiCode);
        }
        return sb.toString();
    }
}
```

```
密文:jxmldqjer
```

#### 1.3.4 凯撒位移加密和解密

```java
package com.gujiangbo.application.kaiser;

import org.apache.commons.lang3.StringUtils;

/**
 * 凯撒位移加密
 * @author gujiangbo
 */
public class KaiserDemo {

    public static void main(String[] args) {
        /**
         * 原始报文
         */
        String msg = "gujiangbo";
        /**
         * 位移量
         */
        int key = 3;
        String encrypt = encrypt(msg, key);
        System.out.println("密文:" + encrypt);
        String decrypt = decrypt(encrypt, key);
        System.out.println("明文:" + decrypt);
    }

    /**
     * 位移加密
     *
     * @param msg 明文
     * @param key 位移量
     * @return 密文
     */
    public static String encrypt(String msg, int key) {
        StringBuffer sb = new StringBuffer();
        if (StringUtils.isBlank(msg)) {
            return null;
        }
        for (char c : msg.toCharArray()) {
            int asciiCode = c;
            asciiCode = asciiCode + key;
            sb.append((char) asciiCode);
        }
        return sb.toString();
    }

    /**
     * 解密
     *
     * @param decryptMsg 密文
     * @param key        位移量
     * @return 明文
     */
    public static String decrypt(String decryptMsg, int key) {
        if (StringUtils.isBlank(decryptMsg)) {
            return null;
        }
        StringBuffer sb = new StringBuffer();
        for (char c : decryptMsg.toCharArray()) {
            int ascillCode = c;
            ascillCode -= key;
            char result = (char) ascillCode;
            sb.append(result);
        }
        return sb.toString();
    }
}

```

```java
密文:jxmldqjer
明文:gujiangbo
```

### 1.4 频度分析法破解恺撒加密

**密码棒**

公元前5世纪的时候，斯巴达人利用一根木棒，缠绕上皮革或者羊皮纸，在上面横向写下信息，解下这条皮带。展开来看，这长串字母没有任何意义。

比如这样：

<img src="https://github.com/gujiangbo520/cryptography/blob/master/src/main/resources/images/juanzhou.png?raw=true" alt="image11" style="zoom: 50%;" />

信差可以将这条皮带当成腰带，系在腰上。

比如这样:

<img src="https://github.com/gujiangbo520/cryptography/blob/master/src/main/resources/images/via.png?raw=true" alt="via" style="zoom:50%;" />

然后收件人将这条皮带缠绕在相同的木棒上，就能恢复信息了。

前404年，一位遍体鳞伤的信差来到斯巴达将领利桑德面前，这趟波斯之旅只有他和四位同伴幸存，利桑德接下腰带，缠绕到他的密码棒上，得知波斯的发那巴祖斯准备侵袭他，多亏密码棒利桑德才能够预先防范，击退敌军。

**频率分析解密法**

密码棒是不是太简单了些？

加密者选择将组成信息的字母替代成别的字母，比如说将a写成1，这样就不能被解密者直接拿到信息了。

这难不倒解密者，以英文字母为例，为了确定每个英文字母的出现频率，分析一篇或者数篇普通的英文文章，英文字母出现频率最高的是e，接下来是t，然后是a……，然后检查要破解的密文，也将每个字母出现的频率整理出来，假设密文中出现频率最高的字母是j，那么就可能是e的替身，如果密码文中出现频率次高的但是P，那么可能是t的替身，以此类推便就能解开加密信息的内容。这就是频率分析法。

- 将明文字母的出现频率与密文字母的频率相比较的过程
- 通过分析每个符号出现的频率而轻易地破译代换式密码
- 在每种语言中，冗长的文章中的字母表现出一种可对之进行分辨的频率。
- e是英语中最常用的字母，其出现频率为八分之一

**频率分析法破解凯撒密码**

创建破解代码：

```java
package com.gujiangbo.application.kaiser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 频率分析法破解凯撒密码
 */
public class FrequencyAnalysis {
	//英文里出现次数最多的字符
	private static final char MAGIC_CHAR = 'e';
	//破解生成的最大文件数
	private static final int DE_MAX_FILE = 4;
	
	public static void main(String[] args) throws Exception {
		//测试1，统计字符个数
		printCharCount("article.txt");
		
		//加密文件
		int key = 3;
		//encryptFile("article.txt", "article_en.txt", key);
		
		//读取加密后的文件
	   // String artile = Util.file2String("article_en.txt");
	    //解密（会生成多个备选文件）
	   // decryptCaesarCode(artile, "article_de.txt");
	}
	
	public static void printCharCount(String path) throws IOException{
		String data = Util.file2String(path);
		List<Entry<Character, Integer>> mapList = getMaxCountChar(data);
		for (Entry<Character, Integer> entry : mapList) {
			//输出前几位的统计信息
			System.out.println("字符'" + entry.getKey() + "'出现" + entry.getValue() + "次");
		}
	}
	
	public static void encryptFile(String srcFile, String destFile, int key) throws IOException {
		String artile = Util.file2String(srcFile);
		//加密文件
		String encryptData = KaiserDemo.encrypt(artile, key);
		//保存加密后的文件
		Util.string2File(encryptData, destFile);
	}
	
	/**
	 * 破解凯撒密码
	 * @param input 数据源
	 * @return 返回解密后的数据
	 */
	public static void decryptCaesarCode(String input, String destPath) {
		int deCount = 0;//当前解密生成的备选文件数
		//获取出现频率最高的字符信息（出现次数越多越靠前）
		List<Entry<Character, Integer>> mapList = getMaxCountChar(input);
		for (Entry<Character, Integer> entry : mapList) {
			//限制解密文件备选数
			if (deCount >= DE_MAX_FILE) {
				break;
			}
			
			//输出前几位的统计信息
			System.out.println("字符'" + entry.getKey() + "'出现" + entry.getValue() + "次");
			
			++deCount;
			//出现次数最高的字符跟MAGIC_CHAR的偏移量即为秘钥
			int key = entry.getKey() - MAGIC_CHAR;
			System.out.println("猜测key = " + key + "， 解密生成第" + deCount + "个备选文件" + "\n");
			String decrypt = KaiserDemo.decrypt(input, key);
			
			String fileName = "de_" + deCount + destPath;
			Util.string2File(decrypt, fileName);
		}
	}
	
	//统计String里出现最多的字符
	public static List<Entry<Character, Integer>> getMaxCountChar(String data) {
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		char[] array = data.toCharArray();
		for (char c : array) {
			if(!map.containsKey(c)) {
				map.put(c, 1);
			}else{
				Integer count = map.get(c);
				map.put(c, count + 1);
			}
		}
		
		//输出统计信息
		/*for (Entry<Character, Integer> entry : map.entrySet()) {
			System.out.println(entry.getKey() + "出现" + entry.getValue() +  "次");
		}*/
		
		//获取获取最大值
		int maxCount = 0;
		for (Entry<Character, Integer> entry : map.entrySet()) {
			//不统计空格
			if (/*entry.getKey() != ' ' && */entry.getValue() > maxCount) { 
				maxCount = entry.getValue();
			}
		}
		
		//map转换成list便于排序
		List<Entry<Character, Integer>> mapList = new ArrayList<Entry<Character,Integer>>(map.entrySet());
		//根据字符出现次数排序
		Collections.sort(mapList, new Comparator<Entry<Character, Integer>>(){
			@Override
			public int compare(Entry<Character, Integer> o1,
					Entry<Character, Integer> o2) {
				return o2.getValue().compareTo(o1.getValue());
			}
		});
		return mapList;
	}
}
```

可以使用上面代码统计工程目录下article.txt 中各个字符个数，统计结果如下：

```java
字符' '出现989次
字符'e'出现478次
字符'a'出现344次
字符't'出现327次
字符'o'出现306次
字符'n'出现279次
字符'i'出现242次
字符'h'出现241次
字符'd'出现214次
字符'l'出现210次
字符'r'出现194次
字符's'出现187次
字符'u'出现118次
字符'w'出现109次
字符'y'出现103次
 ......
```

使用main函数中的encryptFile("article.txt", "article_en.txt", key);方法进行加密，加密后会在项目根目录下生成一个加密文件

<img src="https://github.com/gujiangbo520/cryptography/blob/master/src/main/resources/images/jiamitxt.png?raw=true" alt="jiam" style="zoom:50%;" />

打开密文：

![miwe](https://github.com/gujiangbo520/cryptography/blob/master/src/main/resources/images/miwe.png?raw=true)

我们来看看 频度分析法如何工作的：

```java
public static void main(String[] args) throws Exception {
		//测试1，统计字符个数
		//printCharCount("article_en.txt");
		
		//加密文件
		int key = 3;
		//encryptFile("article.txt", "article_en.txt", key);
		
		//读取加密后的文件
	   String artile = Util.file2String("article_en.txt");
	    //解密（会生成多个备选文件）
	   decryptCaesarCode(artile, "article_de.txt");
	}
```

运行程序：

```shell
字符'#'出现989次
猜测key = -66， 解密生成第1个备选文件

字符'h'出现478次
猜测key = 3， 解密生成第2个备选文件

字符'd'出现344次
猜测key = -1， 解密生成第3个备选文件

字符'w'出现327次
猜测key = 18， 解密生成第4个备选文件

```

运行结果 # 出现次数最多， 我们知道在英文当中 e 出现的频率是最高的，我们假设现在 # 号，就是 e ，变形而来的 ，我们可以对照 ascii 编码表 ，我们的凯撒加密当中位移是加了一个 key ，所以我们 猜测 两个值直接相差 -66 ，我们现在就以 -66 进行解密 生成一个文件，我们查看第一个文件发现，根本读不懂，所以解密失败，我们在猜测 h 是 e ，h 和 e 之间相差3 ，所以我们在去看第二个解密文件，发现我们可以读懂，解密成功

![ascii2](https://github.com/gujiangbo520/cryptography/blob/master/src/main/resources/images/ascii2.png?raw=true)

### 1.5 Byte和bit

Byte : 字节. 数据存储的基本单位，比如移动硬盘1T ， 单位是byte

bit : 比特, 又叫位. 一个位要么是0要么是1. 数据传输的单位 , 比如家里的宽带100MB，下载速度并没有达到100MB，一般都是12-13MB，那么是因为需要使用 100 / 8

关系: 1Byte = 8bit

#### 1.5.1 获取字符串byte

```java
package com.gujiangbo.application.bytebit;
/**
 * ByteBit
 *
 * @author gujiangbo
 */
public class ByteBitTest {

    public static void main(String[] args) {
        String a = "a";
        byte[] bytes = a.getBytes();
        for (byte b : bytes) {
            int c = b;
            // 打印发现byte实际上就是ascii码
            System.out.println(c);
        }
    }
}
```

运行程序:

```shell
97
```

#### 1.5.2 byte对应bit

```java
/**
 * ByteBit
 *
 * @author gujiangbo
 */
public class ByteBitTest {

    public static void main(String[] args) {
        String a = "a";
        byte[] bytes = a.getBytes();
        for (byte b : bytes) {
            int c = b;
            // 打印发现byte实际上就是ascii码
            System.out.println(c);
            // 我们在来看看每个byte对应的bit，byte获取对应的bit
            String s = Integer.toBinaryString(c);
            System.out.println(s);
        }
    }
}
```

运行结果：

打印出来应该是8个bit，但前面是0，没有打印 ，从打印结果可以看出来，一个英文字符 ，占一个字节

```shell
97
1100001
```

#### 1.5.3 中文对应的字节

我们知道在GBK编码下，一个中文占两个字节，在UTF8编码下，一个中文占3个字节。我们验证如下：

```java
public class ByteBitTest02 {
    public static void main(String[] args) throws Exception {
        String a = "顾";
        // 在中文情况下，不同的编码格式，对应不同的字节
        //GBK :编码格式占2个字节
        // UTF-8：编码格式占3个字节
        byte[] bytes = a.getBytes("GBK");
        // byte[] bytes = a.getBytes("UTF-8");
        for (byte b : bytes) {
            System.out.print(b + "   ");
            String s = Integer.toBinaryString(b);
            System.out.println(s);
        }
    }
}
```

输出结果：

```shell
-71   11111111111111111111111110111001
-53   11111111111111111111111111001011
```

我们可以得到GBK下中文汉字对应2个字节以及每个字节对应的二进制码。

打印UTF8下的字节码

```java
public class ByteBitTest02 {
    public static void main(String[] args) throws Exception {
        String a = "顾";
        // 在中文情况下，不同的编码格式，对应不同的字节
        //GBK :编码格式占2个字节
        // UTF-8：编码格式占3个字节
        //byte[] bytes = a.getBytes("GBK");
         byte[] bytes = a.getBytes("UTF-8");
        for (byte b : bytes) {
            System.out.print(b + "   ");
            String s = Integer.toBinaryString(b);
            System.out.println(s);
        }
    }
}
```

输出结果：

```shell
-23   11111111111111111111111111101001
-95   11111111111111111111111110100001
-66   11111111111111111111111110111110
```

可以看出UTF8下，中文汉字对应3个字节

#### 1.5.4 英文对应的字节

我们在看看英文，在不同的编码格式占用多少字节。

GBK下：

```JAVA
public class ByteBitTest03 {
    public static void main(String[] args) throws Exception {
        String a = "A";
        // 在中文情况下，不同的编码格式，对应不同的字节
        //GBK :编码格式占2个字节
        // UTF-8：编码格式占3个字节
        byte[] bytes = a.getBytes("GBK");
        //byte[] bytes = a.getBytes("UTF-8");
        for (byte b : bytes) {
            System.out.print(b + "   ");
            String s = Integer.toBinaryString(b);
            System.out.println(s);
        }
    }
}
```

结果：

```shell
65   1000001
```

UTF8下：

```java
public class ByteBitTest03 {
    public static void main(String[] args) throws Exception {
        String a = "A";
        // 在中文情况下，不同的编码格式，对应不同的字节
        //GBK :编码格式占2个字节
        // UTF-8：编码格式占3个字节
        //byte[] bytes = a.getBytes("GBK");
        byte[] bytes = a.getBytes("UTF-8");
        for (byte b : bytes) {
            System.out.print(b + "   ");
            String s = Integer.toBinaryString(b);
            System.out.println(s);
        }
    }
}
```

结果:

```shell
65   1000001
```

得出结论：英文在GBK与UTF8下一样，都是1个字节。

### 1.6 常见加密方式

![jiam](https://github.com/gujiangbo520/cryptography/blob/master/src/main/resources/images/jiami.png?raw=true)

#### 1.6.1 对称加密

- 采用单钥密码系统的加密方法，同一个密钥可以同时用作信息的加密和解密，这种加密方法称为对称加密，也称为单密钥加密。
- 示例
  - 我们现在有一个原文3要发送给B
  - 设置密钥为108, 3 * 108 = 324, 将324作为密文发送给B
  - B拿到密文324后, 使用324/108 = 3 得到原文
- 常见加密算法
  - DES : Data Encryption Standard，即数据加密标准，是一种使用密钥加密的块算法，1977年被美国联邦政府的国家标准局确定为联邦资料处理标准（FIPS），并授权在非密级政府通信中使用，随后该算法在国际上广泛流传开来。
  - AES : Advanced Encryption Standard, 高级加密标准 .在密码学中又称Rijndael加密法，是美国联邦政府采用的一种区块加密标准。这个标准用来替代原先的DES，已经被多方分析且广为全世界所使用。
- 特点
  - 加密速度快, 可以加密大文件
  - 密文可逆, 一旦密钥文件泄漏, 就会导致数据暴露
  - 加密后编码表找不到对应字符, 出现乱码
  - 一般结合Base64使用

#### 1.6.2 加密解密类-Cipher介绍

Cipher ：文档 `https://docs.oracle.com/javase/8/docs/api/javax/crypto/Cipher.html#getInstance-java.lang.String-`

在对明文进行加密解密中，我们常使用的Cipher类来进行加密解密操作。

此类为加密和解密提供密码功能。它构成了 Java Cryptographic Extension (JCE) 框架的核心。 

为创建 Cipher 对象，应用程序调用 Cipher 的 `getInstance` 方法并将所请求*转换* 的名称传递给它。还可以指定提供者的名称（可选）。 

*转换* 是一个字符串，它描述为产生某种输出而在给定的输入上执行的操作（或一组操作）。转换始终包括加密算法的名称（例如，*DES*），后面可能跟有一个反馈模式和填充方案。 

转换具有以下形式：

- “算法/模式/填充”或
- “*算法*” 

**（**后一种情况下，使用模式和填充方案特定于提供者的默认值）。例如，以下是有效的转换：

```java
     Cipher c = Cipher.getInstance("DES/CBC/PKCS5Padding");
```

使用 `CFB` 和 `OFB` 之类的模式，Cipher 块可以加密单元中小于该 Cipher 的实际块大小的数据。请求这样一个模式时，可以指定一次处理的位数（可选）：将此数添加到模式名称中，正如 " `DES/CFB8/NoPadding`" 和 " `DES/OFB32/PKCS5Padding`" 转换所示。如果未指定该数，则将使用特定于提供者的默认值。（例如，SunJCE 提供者对 DES 使用默认的 64 位）。因此，通过使用如 CFB8 或 OFB8 的 8 位模式，Cipher 块可以被转换为面向字节的 Cipher 流。

**Cipher模式介绍**

| static int     | DECRYPT_MODE  用于将 Cipher 初始化为***解密模式***的常量 |
| -------------- | :------------------------------------------------------- |
| **static int** | **ENCRYPT_MODE 用于将Cipher 初始化为加密模式的常量**     |
| **static int** | **PRIVATE_KEY   用于表示要解包的密钥为“私钥”的常量**     |
| **static int** | **PUBLIC_KEY  用于表示要解包的密钥为“公钥”的常量**       |
| **static int** | **SECRET_KEY用于表示要解包的密钥为“秘密密钥”的常量**     |
| **static int** | **UNWRAP_MODE 用于将 Cipher 初始化为密钥解包模式的常量** |
| **static int** | **WRAP_MODE用于将 Cipher 初始化为密钥包装模式的常量**    |

**方法摘要**

| **方法摘要**                    |                                                              |
| ------------------------------- | ------------------------------------------------------------ |
| ` byte[]`                       | **doFinal()结束多部分加密或解密操作（具体取决于此 Cipher 的初始化方式** |
| ` byte[]`                       | **doFinal(byte[] input)  按单部分操作加密或解密数据，或者结束一个多部分操作。** |
| ` int`                          | **doFinal(byte[] output, int outputOffset)` 结束多部分加密或解密操作（具体取决于此 Cipher 的初始化方式）** |
| ` byte[]`                       | **doFinal(byte[] input, int inputOffset, int inputLen)按单部分操作加密或解密数据，或者结束一个多部分操作。** |
| ` int`                          | **[doFinal](https://tool.oschina.net/uploads/apidocs/jdk-zh/javax/crypto/Cipher.html#doFinal(byte[], int, int, byte[]))**(byte[] input, int inputOffset, int inputLen, byte[] output)  按单部分操作加密或解密数据，或者结束一个多部分操作。 |
| ` int`                          | **[doFinal](https://tool.oschina.net/uploads/apidocs/jdk-zh/javax/crypto/Cipher.html#doFinal(byte[], int, int, byte[], int))**(byte[] input, int inputOffset, int inputLen, byte[] output, int outputOffset)按单部分操作加密或解密数据，或者结束一个多部分操作。 |
| ` int`                          | **[doFinal](https://tool.oschina.net/uploads/apidocs/jdk-zh/javax/crypto/Cipher.html#doFinal(java.nio.ByteBuffer, java.nio.ByteBuffer))**(ByteBuffer input, ByteBuffer output)   按单部分操作加密或解密数据，或者结束一个多部分操作。 |
| ` String`                       | **[getAlgorithm](https://tool.oschina.net/uploads/apidocs/jdk-zh/javax/crypto/Cipher.html#getAlgorithm())**()`       返回此 `Cipher对象的算法名称。 |
| ` int`                          | **[getBlockSize](https://tool.oschina.net/uploads/apidocs/jdk-zh/javax/crypto/Cipher.html#getBlockSize())**()  返回块的大小（以字节为单位）。 |
| ` ExemptionMechanism`           | **[getExemptionMechanism](https://tool.oschina.net/uploads/apidocs/jdk-zh/javax/crypto/Cipher.html#getExemptionMechanism())**()    返回此 Cipher 使用的豁免 (exemption) 机制对象。 |
| `static Cipher`                 | **[getInstance](https://tool.oschina.net/uploads/apidocs/jdk-zh/javax/crypto/Cipher.html#getInstance(java.lang.String))**(String transformation)`       返回实现指定转换的 `Cipher  对象。 |
| `static Cipher`                 | **[getInstance](https://tool.oschina.net/uploads/apidocs/jdk-zh/javax/crypto/Cipher.html#getInstance(java.lang.String, java.security.Provider))**(String transformation, Provider provider)`       返回实现指定转换的 `Cipher对象。 |
| `static Cipher`                 | **[getInstance](https://tool.oschina.net/uploads/apidocs/jdk-zh/javax/crypto/Cipher.html#getInstance(java.lang.String, java.lang.String))**(String transformation, String provider)`       返回实现指定转换的 `Cipher  对象。 |
| ` byte[]`                       | **[getIV](https://tool.oschina.net/uploads/apidocs/jdk-zh/javax/crypto/Cipher.html#getIV())**()  返回新缓冲区中的初始化向量 (IV)。 |
| `static int`                    | **[getMaxAllowedKeyLength](https://tool.oschina.net/uploads/apidocs/jdk-zh/javax/crypto/Cipher.html#getMaxAllowedKeyLength(java.lang.String))**(String transformation) 根据所安装的 JCE 仲裁策略文件，返回指定转换的最大密钥长度。 |
| `static AlgorithmParameterSpec` | **[getMaxAllowedParameterSpec](https://tool.oschina.net/uploads/apidocs/jdk-zh/javax/crypto/Cipher.html#getMaxAllowedParameterSpec(java.lang.String))**(String transformation)   根据仲裁策略文件，返回包含最大 Cipher 参数值的 AlgorithmParameterSpec 对象。 |
| ` int`                          | **[getOutputSize](https://tool.oschina.net/uploads/apidocs/jdk-zh/javax/crypto/Cipher.html#getOutputSize(int))**(int inputLen)`       根据给定的输入长度 `inputLen`（以字节为单位），返回保存下一个 `update` 或 `doFinal 操作结果所需的输出缓冲区长度（以字节为单位）。 |
| ` AlgorithmParameters`          | **[getParameters](https://tool.oschina.net/uploads/apidocs/jdk-zh/javax/crypto/Cipher.html#getParameters())**()  返回此 Cipher 使用的参数。 |
| ` Provider`                     | **[getProvider](https://tool.oschina.net/uploads/apidocs/jdk-zh/javax/crypto/Cipher.html#getProvider())**()`       返回此 `Cipher 对象的提供者。 |
| ` void`                         | **[init](https://tool.oschina.net/uploads/apidocs/jdk-zh/javax/crypto/Cipher.html#init(int, java.security.cert.Certificate))**(int opmode, Certificate certificate)  用取自给定证书的公钥初始化此 Cipher。 |
| ` void`                         | **[init](https://tool.oschina.net/uploads/apidocs/jdk-zh/javax/crypto/Cipher.html#init(int, java.security.cert.Certificate, java.security.SecureRandom))**(int opmode, Certificate certificate, SecureRandom random)  用取自给定证书的公钥和随机源初始化此 Cipher。 |
| ` void`                         | **[init](https://tool.oschina.net/uploads/apidocs/jdk-zh/javax/crypto/Cipher.html#init(int, java.security.Key))**(int opmode, Key key) 用密钥初始化此 Cipher。 |
| ` void`                         | **[init](https://tool.oschina.net/uploads/apidocs/jdk-zh/javax/crypto/Cipher.html#init(int, java.security.Key, java.security.AlgorithmParameters))**(int opmode, Key key, AlgorithmParameters params)  用密钥和一组算法参数初始化此 Cipher。 |
| ` void`                         | **[init](https://tool.oschina.net/uploads/apidocs/jdk-zh/javax/crypto/Cipher.html#init(int, java.security.Key, java.security.spec.AlgorithmParameterSpec))**(int opmode, Key key, AlgorithmParameterSpec params) 用密钥和一组算法参数初始化此 Cipher。 |
| ` void`                         | **[init](https://tool.oschina.net/uploads/apidocs/jdk-zh/javax/crypto/Cipher.html#init(int, java.security.Key, java.security.spec.AlgorithmParameterSpec, java.security.SecureRandom))**(int opmode, Key key, AlgorithmParameterSpec params, SecureRandom random)  用一个密钥、一组算法参数和一个随机源初始化此 Cipher。 |
| ` void`                         | **[init](https://tool.oschina.net/uploads/apidocs/jdk-zh/javax/crypto/Cipher.html#init(int, java.security.Key, java.security.AlgorithmParameters, java.security.SecureRandom))**(int opmode, Key key, AlgorithmParameters params, SecureRandom random) 用一个密钥、一组算法参数和一个随机源初始化此 Cipher。 |
| ` void`                         | **[init](https://tool.oschina.net/uploads/apidocs/jdk-zh/javax/crypto/Cipher.html#init(int, java.security.Key, java.security.SecureRandom))**(int opmode, Key key, SecureRandom random) 用密钥和随机源初始化此 Cipher。 |
| ` Key`                          | **[unwrap](https://tool.oschina.net/uploads/apidocs/jdk-zh/javax/crypto/Cipher.html#unwrap(byte[], java.lang.String, int))**(byte[] wrappedKey, String wrappedKeyAlgorithm, int wrappedKeyType)  解包一个以前包装的密钥。 |
| ` byte[]`                       | **[update](https://tool.oschina.net/uploads/apidocs/jdk-zh/javax/crypto/Cipher.html#update(byte[]))**(byte[] input)继续多部分加密或解密操作（具体取决于此 Cipher 的初始化方式），以处理其他数据部分。 |
| ` byte[]`                       | **[update](https://tool.oschina.net/uploads/apidocs/jdk-zh/javax/crypto/Cipher.html#update(byte[], int, int))**(byte[] input, int inputOffset, int inputLen)  继续多部分加密或解密操作（具体取决于此 Cipher 的初始化方式），以处理其他数据部分。 |
| ` int`                          | **[update](https://tool.oschina.net/uploads/apidocs/jdk-zh/javax/crypto/Cipher.html#update(byte[], int, int, byte[]))**(byte[] input, int inputOffset, int inputLen, byte[] output)     继续多部分加密或解密操作（具体取决于此 Cipher 的初始化方式），以处理其他数据部分。 |
| ` int`                          | **[update](https://tool.oschina.net/uploads/apidocs/jdk-zh/javax/crypto/Cipher.html#update(byte[], int, int, byte[], int))**(byte[] input, int inputOffset, int inputLen, byte[] output, int outputOffset)      继续多部分加密或解密操作（具体取决于此 Cipher 的初始化方式），以处理其他数据部分。 |
| ` int`                          | **[update](https://tool.oschina.net/uploads/apidocs/jdk-zh/javax/crypto/Cipher.html#update(java.nio.ByteBuffer, java.nio.ByteBuffer))**(ByteBuffer input, ByteBuffer output)      继续多部分加密或解密操作（具体取决于此 Cipher 的初始化方式），以处理其他数据部分。 |
| ` byte[]`                       | **[wrap](https://tool.oschina.net/uploads/apidocs/jdk-zh/javax/crypto/Cipher.html#wrap(java.security.Key))**(Key key)       包装密钥。 |

#### 1.6.3 对称加密-DES加密

DES加密算法使用，在使用前，注意两个坑点：

- 坑1： 加密后密文会乱码，需要使用base64进行编码
- 坑2：对称加密方式不同，密钥长度不一样。DES密钥要求8字节，AES密钥要求16字节

```java
package com.gujiangbo.application.des;
import com.sun.org.apache.xml.internal.security.utils.Base64;
import org.apache.commons.lang3.StringUtils;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * DES 加密
 */
public class DesTest {

    public static void main(String[] args) throws Exception {
        String message = "顾江波";
        String algorithm = "DES";
        String transformation = "DES";
        String key = "12345678";
        String encrypt = encrypt(key, algorithm, transformation, message);
        System.out.println(encrypt);
    }

    /**
     * @param key            密钥
     * @param algorithm      加密算法
     * @param transformation 加密类型
     * @param message        明文
     * @return
     */
    public static String encrypt(String key, String algorithm, String transformation, String message) throws Exception {
        String encryptMsg = null;
        if (StringUtils.isBlank(message)) {
            return encryptMsg;
        }
        Cipher cipher = Cipher.getInstance(transformation);
        /**
         * 密钥规则
         * 参数1: 密钥，字节数组
         * 参数2： 算法，使用DES
         */
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), algorithm);
        /**
         * 对加密进行初始化
         * 参数1：表示模式，有加密模式，解密模式
         * 参数2：密钥规则
         */
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        /**
         * 对明文进行加密，
         * 参数：明文字节数组
         */
        byte[] bytes = cipher.doFinal(message.getBytes());
        for (byte b : bytes) {
            int ascii = b;
            System.out.println(ascii + " ");
        }
        return encryptMsg;
    }
}
```

输出结果：

```java
d
vhb ��JaD&|j
```

从结果看，是乱码。如何解决乱码问题，这是后需要使用Base64进行编码操作。

使用Base64进行编码

```java
    public static String encrypt(String key, String algorithm, String transformation, String message) {
        String encryptMsg = null;
        if (StringUtils.isBlank(message)) {
            return encryptMsg;
        }
        try {
            Cipher cipher = Cipher.getInstance(transformation);
            /**
             * 密钥规则
             * 参数1: 密钥，字节数组
             * 参数2： 算法，使用DES
             */
            SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), algorithm);
            /**
             * 对加密进行初始化
             * 参数1：表示模式，有加密模式，解密模式
             * 参数2：密钥规则
             */
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            /**
             * 对明文进行加密，
             * 参数：明文字节数组
             */
            byte[] bytes = cipher.doFinal(message.getBytes());
            /**
             * 解决加密后乱码问题，使用Base64进行编码
             */
            encryptMsg = Base64.encode(bytes);
        } catch (Exception e) {
            e.getStackTrace();
        }
        return encryptMsg;
    }
```

为什么会出现乱码？我们可以将加密后的字节数组转成ascii并输出后分析如下：

ascii 输出结果：

```shell
100 
10 
24 
118 
104 
98 
32 
-97 
-6 
74 
97 
68 
38 
124 
0 
106 
```

我们从ascii 表中得知，十进制数只有0~127，如果这些输出结果不在ascii表中，则会转成乱码。

现在，我们将密钥key长度减少到6个字节(注意：上面代码key为12345678)，会出现以下报错：

```shell
Exception in thread "main" java.security.InvalidKeyException: Invalid key length: 6 bytes
	at com.sun.crypto.provider.DESCipher.engineGetKeySize(DESCipher.java:373)
	at javax.crypto.Cipher.passCryptoPermCheck(Cipher.java:1067)
	at javax.crypto.Cipher.checkCryptoPerm(Cipher.java:1025)
	at javax.crypto.Cipher.implInit(Cipher.java:801)
	at javax.crypto.Cipher.chooseProvider(Cipher.java:864)
	at javax.crypto.Cipher.init(Cipher.java:1249)
	at javax.crypto.Cipher.init(Cipher.java:1186)
	at com.gujiangbo.application.des.DesTest.encrypt(DesTest.java:47)
	at com.gujiangbo.application.des.DesTest.main(DesTest.java:19)
```

#### 1.6.4 对称加密-DES解密

针对上节的加密内容，如何做到解密？在解密中，必须要使用加密的密钥进行解密。

**解密步骤**

- 如果密文使用Base64编码，需要先解码
- 解码后的密文使用Sipher进行解密

相关代码如下：

```java
package com.gujiangbo.application.des;
import com.sun.org.apache.xml.internal.security.utils.Base64;
import org.apache.commons.lang3.StringUtils;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * DES 加密
 */
public class DesTest {

    public static void main(String[] args) throws Exception {
        String message = "顾江波";
        String algorithm = "DES";
        String transformation = "DES";
        String key = "12345678";
        String encryptMsg = encrypt(key, algorithm, transformation, message);
        System.out.println("加密:" + encryptMsg);
        String decryptMsg = decrypt(key, algorithm, transformation, encryptMsg);
        System.out.println("解密:" + decryptMsg);
    }

    /**
     * 解密
     *
     * @param key            密钥
     * @param algorithm      解密算法
     * @param transformation 解密类型
     * @param encryptMsg     密文
     * @return
     */
    public static String decrypt(String key, String algorithm, String transformation, String encryptMsg) throws Exception {
        if (StringUtils.isBlank(encryptMsg)) {
            return null;
        }
        /**
         * 创建加密解密器
         * 参数：解密类型
         */
        Cipher cipher = Cipher.getInstance(transformation);
        /**
         * 密钥规则
         *参数1：密钥字符数组
         *参数2：DES算法
         */
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), algorithm);
        /**
         * 解密初始化
         *参数1：表示模式，有加密模式，解密模式
         *参数2：密钥规则
         */
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        /**
         * 开始解密，如果密文使用Base64编码，需要先解码
         */
        byte[] result = cipher.doFinal(Base64.decode(encryptMsg.getBytes()));
        return new String(result);
    }

    /**
     * @param key            密钥
     * @param algorithm      加密算法
     * @param transformation 加密类型
     * @param message        明文
     * @return
     */
    public static String encrypt(String key, String algorithm, String transformation, String message) throws Exception {
        String encryptMsg = null;
        if (StringUtils.isBlank(message)) {
            return encryptMsg;
        }
        Cipher cipher = Cipher.getInstance(transformation);
        /**
         * 密钥规则
         * 参数1: 密钥，字节数组
         * 参数2： 算法，使用DES
         */
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), algorithm);
        /**
         * 对加密进行初始化
         * 参数1：表示模式，有加密模式，解密模式
         * 参数2：密钥规则
         */
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        /**
         * 对明文进行加密，
         * 参数：明文字节数组
         */
        byte[] bytes = cipher.doFinal(message.getBytes());
        /**
         * 解决加密后乱码问题，使用Base64进行编码
         */
        encryptMsg = Base64.encode(bytes);
        return encryptMsg;
    }
}
```

输出结果：

```java
加密:ZAoYdmhiIJ/6SmFEJnwAag==
解密:顾江波
```

#### 1.6.5 Base64 算法

**Base64介绍**

1. Base64是网络上最常见的用于传输8Bit字节码的可读性编码算法之一
2. 可读性编码算法不是为了保护数据的安全性，而是为了可读性
3. 可读性编码不改变信息内容，只改变信息内容的表现形式
4. 所谓Base64，即是说在编码过程中使用了64种字符：大写A到Z、小写a到z、数字0到9、“+”和“/”
5. Base58是Bitcoin(比特币)中使用的一种编码方式，主要用于产生Bitcoin的钱包地址
6. 相比Base64，Base58不使用数字"0"，字母大写"O"，字母大写"I"，和字母小写"i"，以及"+"和"/"符号

**Base64算法原理**

base64 是 3个字节为一组，一个字节 8位，一共 就是24位 ，然后，把3个字节转成4组，每组6位，

3 * 8 = 4 * 6 = 24 ，每组6位，缺少的2位，会在高位进行补0 ，这样做的好处在于 ，base取的是后面6位，去掉高2位 ，那么base64的取值就可以控制在0-63位了，所以就叫base64，111 111 = 32 + 16 + 8 + 4 + 2 + 1 

**Base64构成原则**

① 小写 a - z = 26个字母

② 大写 A - Z = 26个字母

③ 数字 0 - 9 = 10 个数字

④ + / = 2个符号

大家可能发现一个问题，咱们的base64有个 = 号，但是在映射表里面没有发现 = 号 ， 这个地方需要注意，等号非常特殊，因为base64是三个字节一组 ，如果当我们的位数不够的时候，会使用等号来补齐

![base64](https://github.com/gujiangbo520/cryptography/blob/master/src/main/resources/images/base64.png)

**Base64补等号测试**

```java
import com.sun.org.apache.xml.internal.security.utils.Base64;
public class Base64Test {
    public static void main(String[] args) {
        // 1：MQ== 表示一个字节，不够三个字节，所以需要后面通过 == 号补齐
        System.out.println(Base64.encode("1".getBytes()));
        System.out.println(Base64.encode("12".getBytes()));
        System.out.println(Base64.encode("123".getBytes()));
        // 江苏:中文占6个字节，6 * 8 = 48 ，刚刚好被整除，所以没有等号
        System.out.println(Base64.encode("江苏".getBytes()));
    }
}
```

输出结果

```shell
MQ==
MTI=
MTIz
5rGf6IuP
```

#### 1.6.6 对称加密-AES加密与解密

AES加密与解密方式与DES加密解密方式一样，只需要修改加密算法即可，并且密钥为16字符。

测试代码如下:

```java
package com.gujiangbo.application.aes;
import com.sun.org.apache.xml.internal.security.utils.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AesTest {
    public static void main(String[] args) throws Exception {
        String key = "1234567812345678";
        String transformation = "AES";
        String algorithm = "AES";
        String message = "顾江波";
        String encryptMsg = encrypt(key, algorithm, transformation, message);
        System.out.println("密文:" + encryptMsg);
        String decryptMsg = decrypt(key, algorithm, transformation, encryptMsg);
        System.out.println("解密:" + decryptMsg);

    }

    /**
     * 解密
     *
     * @param key            密钥
     * @param algorithm      算法
     * @param transformation 类型
     * @param encryptMsg     密文
     * @return
     * @throws Exception
     */
    public static String decrypt(String key, String algorithm, String transformation, String encryptMsg) throws Exception {
        Cipher cipher = Cipher.getInstance(transformation);
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), algorithm);
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        return new String(cipher.doFinal(Base64.decode(encryptMsg.getBytes())));
    }

    /**
     * 加密
     *
     * @param key            密钥
     * @param algorithm      加密算法
     * @param transformation 加密类型
     * @param message        明文
     * @return
     */
    public static String encrypt(String key, String algorithm, String transformation, String message) throws Exception {

        Cipher cipher = Cipher.getInstance(transformation);
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        return Base64.encode(cipher.doFinal(message.getBytes()));
    }
}
```

如果将密钥key改成其他位数则报错，报错如下：

```shel
Exception in thread "main" java.security.InvalidKeyException: Illegal key size or default parameters
	at javax.crypto.Cipher.checkCryptoPerm(Cipher.java:1026)
	at javax.crypto.Cipher.implInit(Cipher.java:801)
	at javax.crypto.Cipher.chooseProvider(Cipher.java:864)
	at javax.crypto.Cipher.init(Cipher.java:1249)
	at javax.crypto.Cipher.init(Cipher.java:1186)
	at com.gujiangbo.application.aes.AesTest.encrypt(AesTest.java:51)
	at com.gujiangbo.application.aes.AesTest.main(AesTest.java:14)
```

### 1.7 加密模式

加密模式：`https://docs.oracle.com/javase/8/docs/api/javax/crypto/Cipher.html`

#### [ECB](#ecb)

ECB : Electronic codebook, 电子密码本. 需要加密的消息按照块密码的块大小被分为数个块，并对每个块进行独立加密

​	![ecb](https://github.com/gujiangbo520/cryptography/blob/master/src/main/resources/images/ecb.png?raw=true)

- 优点 : 可以并行处理数据
- 缺点 : 同样的原文生成同样的密文, 不能很好的保护数据
- 同时加密，原文是一样的，加密出来的密文也是一样的

#### [CBC](#cbc)

CBC : Cipher-block chaining, 密码块链接. 每个明文块先与前一个密文块进行异或后，再进行加密。在这种方法中，每个密文块都依赖于它前面的所有明文块

![cbc](https://github.com/gujiangbo520/cryptography/blob/master/src/main/resources/images/cbc.png)

- 优点 : 同样的原文生成的密文不一样
- 缺点 : 串行处理数据.

### 1.8 填充模式

- 当需要按块处理的数据, 数据长度不符合块处理需求时, 按照一定的方法填充满块长的规则

#### [NoPadding](#nopadding)

- 不填充.
- 在DES加密算法下, 要求原文长度必须是8byte的整数倍
- 在AES加密算法下, 要求原文长度必须是16byte的整数倍

#### [PKCS5Padding](#pkcs5padding)

数据块的大小为8位, 不够就补足

#### [Tips](#tips)

- 默认情况下, 加密模式和填充模式为 : ECB/PKCS5Padding
- 如果使用CBC模式, 在初始化Cipher对象时, 需要增加参数, 初始化向量IV : IvParameterSpec iv = new IvParameterSpec(key.getBytes());

加密模式和填充模式

```
AES/CBC/NoPadding (128)
AES/CBC/PKCS5Padding (128)
AES/ECB/NoPadding (128)
AES/ECB/PKCS5Padding (128)
DES/CBC/NoPadding (56)
DES/CBC/PKCS5Padding (56)
DES/ECB/NoPadding (56)
DES/ECB/PKCS5Padding (56)
DESede/CBC/NoPadding (168)
DESede/CBC/PKCS5Padding (168)
DESede/ECB/NoPadding (168)
DESede/ECB/PKCS5Padding (168)
RSA/ECB/PKCS1Padding (1024, 2048)
RSA/ECB/OAEPWithSHA-1AndMGF1Padding (1024, 2048)
RSA/ECB/OAEPWithSHA-256AndMGF1Padding (1024, 2048)
```

加密模式和填充模式例子:

```java
package com.gujiangbo.application.des;

import com.sun.org.apache.xml.internal.security.utils.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class DesTest02 {
    // DES加密算法,key的大小必须是8个字节

    public static void main(String[] args) throws Exception {
        String input = "硅谷";
        // DES加密算法，key的大小必须是8个字节
        String key = "12345678";
        // 指定获取Cipher的算法,如果没有指定加密模式和填充模式,ECB/PKCS5Padding就是默认值
        String transformation = "DES"; // 9PQXVUIhaaQ=
        //String transformation = "DES/ECB/PKCS5Padding"; // 9PQXVUIhaaQ=
        // CBC模式,必须指定初始向量,初始向量中密钥的长度必须是8个字节
        //String transformation = "DES/CBC/PKCS5Padding"; // 9PQXVUIhaaQ=
        // NoPadding模式,原文的长度必须是8个字节的整倍数 ，所以必须把 硅谷改成硅谷12
        // String transformation = "DES/CBC/NoPadding"; // 9PQXVUIhaaQ=
        // 指定获取密钥的算法
        String algorithm = "DES";
        String encryptDES = encryptDES(input, key, transformation, algorithm);
        System.out.println("加密:" + encryptDES);
        String s = decryptDES(encryptDES, key, transformation, algorithm);
        System.out.println("解密:" + s);

    }

    /**
     * 使用DES加密数据
     *
     * @param input          : 原文
     * @param key            : 密钥(DES,密钥的长度必须是8个字节)
     * @param transformation : 获取Cipher对象的算法
     * @param algorithm      : 获取密钥的算法
     * @return : 密文
     * @throws Exception
     */
    private static String encryptDES(String input, String key, String transformation, String algorithm) throws Exception {
        // 获取加密对象
        Cipher cipher = Cipher.getInstance(transformation);
        // 创建加密规则
        // 第一个参数key的字节
        // 第二个参数表示加密算法
        SecretKeySpec sks = new SecretKeySpec(key.getBytes(), algorithm);
        // ENCRYPT_MODE：加密模式
        // DECRYPT_MODE: 解密模式
        // 初始向量，参数表示跟谁进行异或，初始向量的长度必须是8位
//        IvParameterSpec iv = new IvParameterSpec(key.getBytes());
        // 初始化加密模式和算法
        cipher.init(Cipher.ENCRYPT_MODE, sks);
        // 加密
        byte[] bytes = cipher.doFinal(input.getBytes());

        // 输出加密后的数据
        String encode = Base64.encode(bytes);

        return encode;
    }

    /**
     * 使用DES解密
     *
     * @param input          : 密文
     * @param key            : 密钥
     * @param transformation : 获取Cipher对象的算法
     * @param algorithm      : 获取密钥的算法
     * @throws Exception
     * @return: 原文
     */
    private static String decryptDES(String input, String key, String transformation, String algorithm) throws Exception {
        // 1,获取Cipher对象
        Cipher cipher = Cipher.getInstance(transformation);
        // 指定密钥规则
        SecretKeySpec sks = new SecretKeySpec(key.getBytes(), algorithm);
//        IvParameterSpec iv = new IvParameterSpec(key.getBytes());
        cipher.init(Cipher.DECRYPT_MODE, sks);
        // 3. 解密
        byte[] bytes = cipher.doFinal(Base64.decode(input));

        return new String(bytes);
    }
}
```

程序结果：

```java
加密:qANksk5lvqM=
解密:硅谷
```

**修改成 `CBC` 加密 模式 **

- **方式一** **使用PKCS5Padding填充**

```java
String transformation = "DES/CBC/PKCS5Padding";
```

运行 ，报错，需要添加一个参数

```java
Exception in thread "main" java.security.InvalidKeyException: Parameters missing
	at com.sun.crypto.provider.CipherCore.init(CipherCore.java:470)
	at com.sun.crypto.provider.DESCipher.engineInit(DESCipher.java:186)
	at javax.crypto.Cipher.implInit(Cipher.java:802)
	at javax.crypto.Cipher.chooseProvider(Cipher.java:864)
	at javax.crypto.Cipher.init(Cipher.java:1249)
	at javax.crypto.Cipher.init(Cipher.java:1186)
	at com.gujiangbo.application.des.DesTest02.decryptDES(DesTest02.java:79)
	at com.gujiangbo.application.des.DesTest02.main(DesTest02.java:26)
```

修改加密代码：

```java
 String transformation = "DES/CBC/PKCS5Padding"; 
//加密时加上这个
// 初始向量，参数表示跟谁进行异或，初始向量的长度必须是8位
IvParameterSpec iv = new IvParameterSpec(key.getBytes());
// 初始化加密模式和算法
 cipher.init(Cipher.ENCRYPT_MODE, sks, iv);

//解密加上这个
IvParameterSpec iv = new IvParameterSpec(key.getBytes());
cipher.init(Cipher.DECRYPT_MODE, sks,iv);
```

修改后运行结果：

```shell
加密:8Ze/OtPlSaU=
解密:硅谷
```

- **方式二 使用NoPadding填充**

```java
String transformation = "DES/CBC/NoPadding";
```

运行报错 `NoPadding` 这种填充模式 **原文必须是8个字节的整倍数**

```java
Exception in thread "main" javax.crypto.IllegalBlockSizeException: Input length not multiple of 8 bytes
	at com.sun.crypto.provider.CipherCore.finalNoPadding(CipherCore.java:1041)
	at com.sun.crypto.provider.CipherCore.doFinal(CipherCore.java:1009)
	at com.sun.crypto.provider.CipherCore.doFinal(CipherCore.java:847)
	at com.sun.crypto.provider.DESCipher.engineDoFinal(DESCipher.java:314)
	at javax.crypto.Cipher.doFinal(Cipher.java:2165)
	at com.gujiangbo.application.des.DesTest02.encryptDES(DesTest02.java:55)
	at com.gujiangbo.application.des.DesTest02.main(DesTest02.java:24)
```

修改运行

将原文修改成8个字节的整数倍

```java
String input = "硅谷12";
```

结果输出：

```java
加密:Y6htKI/ceJg=
解密:硅谷12
```

注意：在使用此种填充方式时，必须要在加密与解密中使用IvParameterSpec类，否则报错。

```java
   /**
     * 使用DES加密数据
     *
     * @param input          : 原文
     * @param key            : 密钥(DES,密钥的长度必须是8个字节)
     * @param transformation : 获取Cipher对象的算法
     * @param algorithm      : 获取密钥的算法
     * @return : 密文
     * @throws Exception
     */
    private static String encryptDES(String input, String key, String transformation, String algorithm) throws Exception {
        // 获取加密对象
        Cipher cipher = Cipher.getInstance(transformation);
        // 创建加密规则
        // 第一个参数key的字节
        // 第二个参数表示加密算法
        SecretKeySpec sks = new SecretKeySpec(key.getBytes(), algorithm);
        // ENCRYPT_MODE：加密模式
        // DECRYPT_MODE: 解密模式
        // 初始向量，参数表示跟谁进行异或，初始向量的长度必须是8位
        IvParameterSpec iv = new IvParameterSpec(key.getBytes());
        // 初始化加密模式和算法
        cipher.init(Cipher.ENCRYPT_MODE, sks,iv);
        // 加密
        byte[] bytes = cipher.doFinal(input.getBytes());

        // 输出加密后的数据
        String encode = Base64.encode(bytes);

        return encode;
    }
```

解密代码：

```java
   /**
     * 使用DES解密
     *
     * @param input          : 密文
     * @param key            : 密钥
     * @param transformation : 获取Cipher对象的算法
     * @param algorithm      : 获取密钥的算法
     * @throws Exception
     * @return: 原文
     */
    private static String decryptDES(String input, String key, String transformation, String algorithm) throws Exception {
        // 1,获取Cipher对象
        Cipher cipher = Cipher.getInstance(transformation);
        // 指定密钥规则
        SecretKeySpec sks = new SecretKeySpec(key.getBytes(), algorithm);
        IvParameterSpec iv = new IvParameterSpec(key.getBytes());
        cipher.init(Cipher.DECRYPT_MODE, sks,iv);
        // 3. 解密
        byte[] bytes = cipher.doFinal(Base64.decode(input));

        return new String(bytes);
    }
```

在测试 `AES` 的时候需要注意，key需要16个字节，加密向量也需要16个字节 ，其他方式跟 `DES` 一样

### 1.9 消息摘要

- 消息摘要（Message Digest）又称为数字摘要(Digital Digest)
- 它是一个唯一对应一个消息或文本的固定长度的值，它由一个单向Hash加密函数对消息进行作用而产生
- 使用数字摘要生成的值是不可以篡改的，为了保证文件或者值的安全

#### 1.9.1 特点

无论输入的消息有多长，计算出来的消息摘要的长度总是固定的。例如应用MD5算法摘要的消息有128个比特位，用SHA-1算法摘要的消息最终有160比特位的输出

只要输入的消息不同，对其进行摘要以后产生的摘要消息也必不相同；但相同的输入必会产生相同的输出

消息摘要是单向、不可逆的

常见算法 :

```shell
- MD5
- SHA1
- SHA256
- SHA512
```

**常见的工具数字摘要**

我们经常使用tomcat,在官网下载页面 我们可以查看到下载链接后面会有sha1,sha512 等字样，这些都是对软件的一种数字摘要

#### 1.9.2 获取字符串消息摘要

```java
package com.gujiangbo.application.digest;
import com.sun.org.apache.xml.internal.security.utils.Base64;
import java.security.MessageDigest;
/**
 * 数字摘要
 *
 * @author gujiangbo
 */
public class DigestTest {
    public static void main(String[] args) throws Exception {
        /**
         * 原文
         */
        String msg = "顾江波";
        /**
         * 摘要算法
         */
        String algorithm = "MD5";
        /**
         * 获取数字摘要对象
         */
        MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
        /**
         * 获取数字摘要结果字符数组
         */
        byte[] digest = messageDigest.digest();
        /**
         * 使用Base64对数组编码
         */
        String encode = Base64.encode(digest);
        System.out.println(encode);
    }
}
```

输出结果：

```shell
1B2M2Y8AsgTpgAmY7PhCfg==
```

如果结果不用Base64编码，则输出为：��ُ��	���B~   乱码~

#### 1.9.3 将数字摘要转换成16进制

```java
package com.gujiangbo.application.digest;

import java.security.MessageDigest;

/**
 * 数字摘要转换成16进制
 *
 * @author gujiangbo
 */
public class DigestTest02 {

    public static void main(String[] args) throws Exception {
        /**
         * 原文
         */
        String msg = "顾江波";
        /**
         * 摘要算法
         */
        String algorithm = "MD5";
        /**
         * 获取数字摘要对象
         */
        MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
        /**
         * 获取数字摘要结果字符数组
         */
        byte[] digest = messageDigest.digest();
        StringBuilder sb = new StringBuilder();

        for (byte b : digest) {
            // 转成 16进制
            String s = Integer.toHexString(b & 0xff);
            //System.out.println(s);
            if (s.length() == 1) {
                // 如果生成的字符只有一个，前面补0
                s = "0" + s;
            }
            sb.append(s);
        }
        System.out.println(sb.toString());
    }
}
```

输出结果：d41d8cd98f00b204e9800998ecf8427e

#### 1.9.4 其他的数字摘要算法

```JAVA
package com.gujiangbo.application.digest;
import java.security.MessageDigest;
public class DigestTest03 {
    public static void main(String[] args) throws Exception {
        // 原文
        String input = "aa";
        // 算法
        String algorithm = "MD5";
        // 获取数字摘要对象
        String md5 = getDigest(input, "MD5");
        System.out.println("MD5:" + md5);

        String sha1 = getDigest(input, "SHA-1");
        System.out.println("SHA-1:" + sha1);

        String sha256 = getDigest(input, "SHA-256");
        System.out.println("SHA-256:" + sha256);

        String sha512 = getDigest(input, "SHA-512");
        System.out.println("SHA-512:" + sha512);
    }

    private static String toHex(byte[] digest) throws Exception {
//        System.out.println(new String(digest));
        // base64编码
//        System.out.println(Base64.encode(digest));
        // 创建对象用来拼接
        StringBuilder sb = new StringBuilder();

        for (byte b : digest) {
            // 转成 16进制
            String s = Integer.toHexString(b & 0xff);
            if (s.length() == 1) {
                // 如果生成的字符只有一个，前面补0
                s = "0" + s;
            }
            sb.append(s);
        }
        System.out.println("16进制数据的长度：" + sb.toString().getBytes().length);
        return sb.toString();
    }
    private static String getDigest(String input, String algorithm) throws Exception {
        MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
        // 消息数字摘要
        byte[] digest = messageDigest.digest(input.getBytes());
        System.out.println("密文的字节长度:" + digest.length);
        return toHex(digest);
    }
}
```

输出结果：

```java
密文的字节长度:16
16进制数据的长度：32
MD5:4124bc0a9335c27f086f24ba207a4912
密文的字节长度:20
16进制数据的长度：40
SHA-1:e0c9035898dd52fc65c41454cec9c4d2611bfb37
密文的字节长度:32
16进制数据的长度：64
SHA-256:961b6dd3ede3cb8ecbaacbd68de040cd78eb2ed5889130cceb4c49268ea4d506
密文的字节长度:64
16进制数据的长度：128
SHA-512:f6c5600ed1dbdcfdf829081f5417dccbbd2b9288e0b427e65c8cf67e274b69009cd142475e15304f599f429f260a661b5df4de26746459a3cef7f32006e5d1c1
```

总结：

- MD5算法 : 摘要结果16个字节, 转16进制后32个字节
- SHA1算法 : 摘要结果20个字节, 转16进制后40个字节
- SHA256算法 : 摘要结果32个字节, 转16进制后64个字节
- SHA512算法 : 摘要结果64个字节, 转16进制后128个字节

### 1.10 非对称加密

**简介：**

① 非对称加密算法又称`现代加密算法`。

② 非对称加密是计算机通信安全的基石，保证了加密数据`不会被破解`。

③ 与对称加密算法不同，非对称加密算法需要两个密钥：`公开密钥(publickey)` 和`私有密(privatekey)`

④ 公开密钥和私有密钥是`一对`

⑤ 如果用`公开密钥`对数据进行`加密`，只有用`对应的私有密钥`才能`解密`。

⑥ 如果用`私有密钥`对数据进行`加密`，只有用`对应的公开密钥`才能`解密`。

⑦ 因为加密和解密使用的是两个`不同`的密钥，所以这种算法叫作`非对称加密算法`。

- 示例

  - 首先生成密钥对, 公钥为(5,14), 私钥为(11,14)
  - 现在A希望将原文2发送给B
  - A使用公钥加密数据. 2的5次方mod 14 = 4 , 将密文4发送给B
  - B使用私钥解密数据. 4的11次方mod14 = 2, 得到原文2

- 特点

  - 加密和解密使用不同的密钥
  - 如果使用私钥加密, 只能使用公钥解密
  - 如果使用公钥加密, 只能使用私钥解密
  - 处理数据的速度较慢, 因为安全级别高

- 常见算法

   

  - RSA
  - ECC

#### 1.10.1 生成公钥和私钥

```java
package com.gujiangbo.application.rsa;
import com.sun.org.apache.xml.internal.security.utils.Base64;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * 非对称加密RSA
 *
 * @author gujiangbo
 */
public class RsaTest02 {

    public static void main(String[] args) throws Exception {
        /**
         * 加密算法
         */
        String algorithm = "RSA";
        /**
         * 创建密钥对生成器对象
         */
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(algorithm);
        /**
         * 生成密钥对
         */
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        /**
         * 生成私钥
         */
        PrivateKey privateKey = keyPair.getPrivate();
        /**
         * 生成公钥
         */
        PublicKey publicKey = keyPair.getPublic();

        /**
         * 获取私钥字节数组
         */
        byte[] privateKeyEncoded = privateKey.getEncoded();

        /**
         * 获取公钥字节数组
         */
        byte[] publicKeyEncoded = publicKey.getEncoded();

        /**
         * 使用Base64对数组进行编码，防止出现乱码
         */
        System.out.println(Base64.encode(privateKeyEncoded));
        System.out.println(Base64.encode(publicKeyEncoded));
    }
}
```

输出结果：

```shell
MIICeQIBADANBgkqhkiG9w0BAQEFAASCAmMwggJfAgEAAoGBAK2V7SvD8sLjNXtpehi9nv11vWbH
B8nui5DCz++17ywdH+IIaSIZuXWZYTtHy6C+TRKVSMST0vAvSFYWoddOrDw1OSfnN3dX8b6JcenB
LkJY/B/dzCqfc012Ax0MBaAVLX5e/2gfe9U0vehTwpwI6L0N06e9T1QBsIy059AL3XEvAgMBAAEC
gYEAiSMpfKoR4GYgmp96FpG4SgX63Hfhb2dGW9eM75SMoA1iYvDCHm60VnQWnP7boOK3gTbvhl5D
B/5S57B5q+A7sQhDDERj5rwEtta7nvOhPrAq6LN9nWA5i6XzDEPktTCTGO/j/M1J4+trnHGE/Jpa
zpVmSPRgK7QoaPnewA1QUAECQQDUa6an1ZCHzzsEDHBuaVT3u62q/c7U8d/EGDK+Qfm5h58D4+/X
YsSXd86d8iUNwAtvU7THGXzWYZsUtTwZBCFhAkEA0TKqCBVtJGjUE9yBojHZrKzcsD5LgUtqsPmK
JpmY9lidNIROlZe2zFi7RF0Nc5tb8SVMEOeFbVhLAkF7DGZMjwJBAJSewRp6PMYYgqUBgwRtI+q0
X/zb2YN5u2K6v34IMQ3SyrnxF5St4PhM9b3idnRZYmbAvSH0PjjMT7G8X9Ds5+ECQQCKw+ClnBDZ
pe+HfS1AOXD6aW+6OJg9G5mZ6u0Izbn5Lq6Yt2qpMAnBYtpVbqQNm3BRdTwPuoN3FKosxOKqRvFh
AkEAsmYVz7S/bMfVJOO6IJXYXMflX99J2wA2MIRLI3HUpGrV6YUh4Ey1dwcg6StjzWwL9CPHhGu7
gd03aqN223T7gw==
MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCtle0rw/LC4zV7aXoYvZ79db1mxwfJ7ouQws/v
te8sHR/iCGkiGbl1mWE7R8ugvk0SlUjEk9LwL0hWFqHXTqw8NTkn5zd3V/G+iXHpwS5CWPwf3cwq
n3NNdgMdDAWgFS1+Xv9oH3vVNL3oU8KcCOi9DdOnvU9UAbCMtOfQC91xLwIDAQAB
```

#### 1.10.2 使用公钥加密，私钥解密

```java
package com.gujiangbo.application.rsa;

import com.sun.org.apache.xml.internal.security.utils.Base64;

import javax.crypto.Cipher;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.HashMap;

/**
 * 使用公钥加密，私钥解密
 *
 * @author gujiangbo
 */
public class RsaTest03 {

    public static void main(String[] args) throws Exception {
        /**
         * 明文
         */
        String msg = "锄禾日当午，汗滴禾下土；谁知盘中餐，粒粒皆辛苦。";
        /**
         * 加密算法
         */
        String algorithm = "RSA";
        /**
         * 获取公私钥
         */
        HashMap<Key, Object> map = getKey(algorithm);
        String encryMsg = encryptRSA(msg, algorithm, (PublicKey) map.get(Key.PUBLIC));
        System.out.println("密文:" + encryMsg);
        String result = decryptRSA(encryMsg, algorithm, (PrivateKey) map.get(Key.PRIVATE));
        System.out.println("明文:" + result);

    }

    /**
     * 解密
     *
     * @param encryptMsg 密文
     * @param algorithm  算法
     * @param privateKey 私钥
     * @return 返回明文
     * @throws Exception
     */
    public static String decryptRSA(String encryptMsg, String algorithm, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] msg = cipher.doFinal(Base64.decode(encryptMsg.getBytes()));
        return new String(msg);
    }
    /**
     * 加密
     *
     * @param msg       原文
     * @param algorithm 算法
     * @param publicKey 公钥
     * @return 加密对象
     */
    public static String encryptRSA(String msg, String algorithm, PublicKey publicKey) throws Exception {
        /**
         * 生成加密器
         */
        Cipher cipher = Cipher.getInstance(algorithm);
        /**
         * 初始化加密器
         */
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptMsg = cipher.doFinal(msg.getBytes());
        return Base64.encode(encryptMsg);
    }

    /**
     * 获取公私钥
     *
     * @param algorithm 加密算法
     * @return 公私钥Hash
     */
    public static HashMap<Key, Object> getKey(String algorithm) throws Exception {
        HashMap<Key, Object> map = new HashMap<>();
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(algorithm);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();
        map.put(Key.PRIVATE, privateKey);
        map.put(Key.PUBLIC, publicKey);
        return map;
    }

    /**
     * 公私钥枚举值
     */
    enum Key {
        PUBLIC, PRIVATE
    }
}
```

结果输出：

```shell
密文:SaIypz+LZse+6Z1AB+M1qrOoGe1vugsFpVoDNGRSZbf6ouXvTwjaRg46eZ4QXh0vAvItIQp8RKuG
3/NqM26+THkegNkxbfqeU9mkCyn9OG+yKdsbIaeXg3qamJ9Pm3+ME72ISg/NGF10v7DdwQwrxydE
wut/8wf7FI9gmfOmPdw=
明文:锄禾日当午，汗滴禾下土；谁知盘中餐，粒粒皆辛苦。
```

#### 1.10.3 将公私钥保存本地，并读取解密

```java
package com.gujiangbo.application.rsa;

import com.sun.org.apache.xml.internal.security.utils.Base64;
import org.apache.commons.io.FileUtils;

import javax.crypto.Cipher;
import java.io.File;
import java.nio.charset.Charset;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * 保存公私钥
 *
 * @author gujiangbo
 */
public class RsaTest04 {

    public static void main(String[] args) throws Exception {
        /**
         * 明文
         */
        String msg = "顾江波";
        /**
         * 加密算法
         */
        String algorithm = "RSA";

        /**
         *  生成密钥对并保存在本地文件中
         */

        generateKeyToFile(algorithm, "g.pub", "g.pri");
        PublicKey publicKey = getPublicKey("g.pub", algorithm);
        PrivateKey privateKey = getPrivateKey("g.pri", algorithm);

        String encryptMsg = encryptRSA(msg, algorithm, publicKey);
        System.out.println("密文:" + encryptMsg);
        String decryptMsg = decryptRSA(encryptMsg, algorithm, privateKey);
        System.out.println("明文:" + decryptMsg);

    }

    /**
     * 获取公钥
     *
     * @param pubPath   公钥路径
     * @param algorithm 算法
     * @return 返回公钥key对象
     */
    public static PublicKey getPublicKey(String pubPath, String algorithm) throws Exception {
        String publicKeyString = FileUtils.readFileToString(new File(pubPath), Charset.defaultCharset());
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.decode(publicKeyString));
        return keyFactory.generatePublic(keySpec);
    }

    /**
     * 读取私钥
     *
     * @param priPath   私钥路径
     * @param algorithm 算法
     * @return 返回私钥key对象
     */
    public static PrivateKey getPrivateKey(String priPath, String algorithm) throws Exception {
        String privateKeyString = FileUtils.readFileToString(new File(priPath), Charset.defaultCharset());
        //创建Key工厂
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        //创建私钥key规则
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.decode(privateKeyString));
        return keyFactory.generatePrivate(keySpec);
    }

    /**
     * 解密
     *
     * @param encryptMsg 密文
     * @param algorithm  算法
     * @param privateKey 私钥
     * @return 返回明文
     * @throws Exception
     */
    public static String decryptRSA(String encryptMsg, String algorithm, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] msg = cipher.doFinal(Base64.decode(encryptMsg.getBytes()));
        return new String(msg);
    }

    /**
     * 加密
     *
     * @param msg       原文
     * @param algorithm 算法
     * @param publicKey 公钥
     * @return 加密对象
     */
    public static String encryptRSA(String msg, String algorithm, PublicKey publicKey) throws Exception {

        /**
         * 生成加密器
         */
        Cipher cipher = Cipher.getInstance(algorithm);
        /**
         * 初始化加密器
         */
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptMsg = cipher.doFinal(msg.getBytes());
        return Base64.encode(encryptMsg);
    }
    /**
     * 生成公私钥
     *
     * @param algorithm 算法
     * @param pubPath   公钥路径
     * @param priPath   私钥路径
     * @throws Exception
     */
    public static void generateKeyToFile(String algorithm, String pubPath, String priPath) throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(algorithm);
        /**
         * 获取密钥对
         */
        KeyPair keyPair = keyPairGenerator.genKeyPair();
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();
        byte[] privateKeyEncoded = privateKey.getEncoded();
        byte[] publicKeyEncoded = publicKey.getEncoded();
        /**
         * 保存公钥,私钥
         */
        FileUtils.writeStringToFile(new File(pubPath), Base64.encode(publicKeyEncoded), Charset.forName("UTF-8"));
        FileUtils.writeStringToFile(new File(priPath), Base64.encode(privateKeyEncoded), Charset.forName("UTF-8"));
    }
}
```

结果：

```she
密文:oH1skUkTUBtLmFlerG4PojED1IraF+VTXf6ZiogZWazOIf61LfDYbjn/tIp6TXv/sAwqe6xPJYcP
cw407C89RZhxjOPoAJjSXm55s2sTWUd9i8MB+uDbp63syYiE6a94UygvAsxVfCfX/40t7KruglNH
zLV8+iZmVE+HXRYp3g8=
明文:顾江波
```
