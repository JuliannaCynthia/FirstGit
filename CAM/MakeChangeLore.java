package com.techelevator;

import java.util.Scanner;

public class MakeChangeLore {

    public static double getBillInDollars(){
        Scanner inputScanner = new Scanner(System.in);
        System.out.println();
        System.out.println("Now that you've met our fancy felines, lets make a decision. \n");
        System.out.println("If you think Lyle is the lad for you, please press ~1~ then ~enter~ \n" );
        System.out.println("If you think PaintBrush is the new Van Gogh, please press ~2~ then ~enter~\n");
        System.out.println("If you think SpaceMan is secretly the dog from MIB, please press ~3~ then ~enter~");
        return Double.parseDouble(inputScanner.nextLine());
    }

    public static double getTotalCatFee(){
        final double VET_COST = 100.0;
        final double ADOPTION_FEE = 265.0;
        return VET_COST + ADOPTION_FEE;
    }
    public static double getBillWithServiceFee(){
        double addedServiceFee = MakeChangeLore.getTotalCatFee();
        if (addedServiceFee >= 50.0){
            addedServiceFee += addedServiceFee * 0.1;
        }
        return addedServiceFee;
    }
    public static double getAmountTendered() {
        Scanner inputScanner = new Scanner(System.in);
        System.out.println();
        System.out.println("Your new cat is going to cost $401.50.\nRight now, our card reader is down, and we are only accepting cash.\nPlease enter the total amount that you have in cash\nto pay for your new furry friend: \n");
        System.out.println("   /\\     /\\");
        System.out.println("  {  `---'  }");
        System.out.println("  {  O   O  } ");
        System.out.println("~~|~   V   ~|~~ ");
        System.out.println("   \\  \\|/  /  ");
        System.out.println("    `-----'__");
        System.out.println("    /     \\  `^\\_");
        System.out.println("   {       }\\ |\\_\\_   W");
        System.out.println("   |  \\_/  |/ /  \\_\\_( )");
        System.out.println("    \\__/  /(_E     \\__/");
        System.out.println("      (  /");
        System.out.println("       MM");
        return Double.parseDouble(inputScanner.nextLine());
    }
    public static void getStartMenu(){
        Scanner inputScanner = new Scanner(System.in);
        System.out.println();
        System.out.println("***************************************************\n*****~ Welcome to the {Cat Adoption Service} ~*****\n***************************************************\n\nWe are excited to introduce to you our funny, furry, feline friends!\n\nPlease press ~enter~ to meet the cats!\n");
        System.out.println("   |\\_._/|        |,\\__/|        |\\__/,|        |\\_._/|");
        System.out.println("   | o o |        |  o o|        |o o  |        | 0 0 |");
        System.out.println("   (  T  )        (   T )        ( T   )        (  T  )");
        System.out.println("  .^`-^-'^.      .^`--^'^.      .^`^--'^.      .^`-^-'^.");
        System.out.println("  `.  ;  .'      `.  ;  .'      `.  ;  .'      `.  ;  .'");
        System.out.println("  | | | | |      | | | | |      | | | | |      | | | | |");
        System.out.println(" ((_((|))_))    ((_((|))_))    ((_((|))_))    ((_((|))_))");
        inputScanner.nextLine();



    }
    public static void endMenu() {
        Scanner inputScanner = new Scanner(System.in);
        System.out.println("\n*********************************************************\n**~ Thank you for visiting the {Cat Adoption Service} ~**\n*********************************************************\n\n\nIf you need any other 'FUR'ther information, please reach out\nto us at <imachunkycat@gmail.org>, or at our store operating\nservice @ 1-800-BIG-MEOW. Have a meoooowy good day!\n\nPlease press ~enter~ to exit the menu.\n");
        System.out.println("             *     ,MMM8&&&.            *");
        System.out.println("                  MMMM88&&&&&    .");
        System.out.println("                 MMMM88&&&&&&&");
        System.out.println("   *             MMM88&&&&&&&&");
        System.out.println("                 MMM88&&&&&&&&");
        System.out.println("                 'MMM88&&&&&&'");
        System.out.println("                   'MMM8&&&'      *   _");
        System.out.println("           |\\___/|                     \\\\");
        System.out.println("          =) ^Y^ (=   |\\_/|             ||    '");
        System.out.println("           \\  ^  /    )a a '._.-\"\"\"\"-.  //");
        System.out.println("            )=*=(    =\\T_= /    ~  ~  \\//");
        System.out.println("           /     \\     `\"`\\   ~   / ~  /");
        System.out.println("           |     |         |~   \\  |  ~/");
        System.out.println("          /| | | |\\          \\  ~/- \\ ~\\");
        System.out.println("          \\| | |_|/|         || |   // /`");
        System.out.println("  jgs_/\\_//_// __//\\_/\\_/\\_((_|\\((_//\\_/\\_/\\_");
        System.out.println("  |  |  |  | \\_) |  |  |  |  |  |  |  |  |  |");
        System.out.println("  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |");
        System.out.println("  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |");
        System.out.println("  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |");
        System.out.println("  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |");
        inputScanner.nextLine();

    }
    public static void meetLyle(){
        Scanner inputScanner = new Scanner(System.in);
        System.out.println();
        System.out.println("\n*********~ Lyle ~*********\n\nMeet Lyle! Lyle is a pure 'bread' loaf who just wants to be loved.\nHe is a standard Maine-Coon, who has a 'toe' up on the competition\ndue to his funky polydactyly condition!\nWeighing in at a whopping 19lbs, Lyle's crumb structure is certainly dense.\nWhile active and playful definitely describe this feline friend,\nwe ask that Lyle bakes in the oven all on his own as his dislike\nfor other bread types is 'rising'!\nIf Lyle suits your style, lets get together and make biscuits! \n\nPlease press ~enter~ to meet the next cat!");
        System.out.println("                _                       ");
        System.out.println("                \\`*-.                   ");
        System.out.println("                 )  _`-.                ");
        System.out.println("                .  : `. .               ");
        System.out.println("                : _   '  \\              ");
        System.out.println("                ; *` _.   `*-._         ");
        System.out.println("                `-.-'          `-.      ");
        System.out.println("                  ;       `       `.    ");
        System.out.println("                  :.       .        \\   ");
        System.out.println("                  . \\  .   :   .-'   .  ");
        System.out.println("                  '  `+.;  ;  '      :  ");
        System.out.println("                  :  '  |    ;       ;-.");
        System.out.println("                  ; '   : :`-:     _.`* ;");
        System.out.println("         [bug] .*' /  .*' ; .*`- +'  `*'");
        System.out.println("               `*-*   `*-*  `*-*'       \n");
        inputScanner.nextLine();
    }
    public static void meetPaintBrush() {
        Scanner inputScanner = new Scanner(System.in);
        System.out.println();
        System.out.println("\n*********~ Paint Brush ~*********\n\nMeet Paint Brush! Paint Brush is a multi-color kitty looking\nfor an artist to inspire. She is a rare calico cat with black mitts!\nLooks like someone spent too much time playing in the ink.\nPaint Brush, or PB for short, is also short. She's a munchkin,\nso this painted cat is certainly getting places.... just not high ones!\nPB would really prefer help getting her canvas done, so we ask\nthat PB not be the only brush in the bucket.\nLet us know if PB, is the muse, for YOU!\n\nPlease press ~enter~ to meet the next cat!");
        System.out.println("  /\\___/\\");
        System.out.println(" ( o   o )");
        System.out.println(" (  =^=  )");
        System.out.println(" (        )");
        System.out.println(" (         )");
        System.out.println(" (          )))))))))))");
        inputScanner.nextLine();
    }
    public static void meetSpaceMan() {
        Scanner inputScanner = new Scanner(System.in);
        System.out.println();
        System.out.println("\n*********~ SpaceMan ~*********\n\nAre you looking for a cat from a galaxy far, far away?\nIf yes, then SpaceMan is futuristic furry friend for you!\nA tuxedo cat by birth, SpaceMan is always dressed to impress,\nand certainly has no problem fooling those nasty intergalactic spies!\nSpaceMan is a little larger-than-life, and thus both his tummy\nand his food bill are of planetary proportions. We recommend\na strict freeze-dried diet for SpaceMan, because he's from space.\nSpace cats only eat astronaut food, duh!\nIf SpaceMan is the intergalactic co-cat for you, that's Out-Of-This-WORLD!\n\nPlease press ~enter~ to get to the cat menu!\n");
        System.out.println(" /\\-/\\    /\\-/\\    /\\-/\\    /\\-/\\    /\\-/\\    /\\-/\\    /\\-/\\    /\\-/\\");
        System.out.println("(=^Y^=)  (=^Y^=)  (=^Y^=)  (=^Y^=)  (=^Y^=)  (=^Y^=)  (=^Y^=)  (=^Y^=)");
        System.out.println(" (>o<)    (>o<)    (>o<)    (>o<)    (>o<)    (>o<)    (>o<)    (>o<)");
        inputScanner.nextLine();
    }

}
