public class Main {

    public static void main(String[] a){

        /* entities */
        Entity player1 = new Entity();
        player1.set(UserAppKeys.NAME, "Player1");
        player1.set(UserAppKeys.Speed, 12.5f);
        player1.set(UserAppKeys.Health, 150);

        Entity player2 = new Entity();
        player1.set(UserAppKeys.NAME, "Player2");
        player1.set(UserAppKeys.Health, 250);

        /*get or default methods*/
        Float playerOneSpeed = player1.getOrDefault(UserAppKeys.Speed, 0.0f);
        Float playerTwoSpeed = player2.getOrDefault(UserAppKeys.Speed, 0.0f);

        System.out.println(
                "Player 1 speed: " + playerOneSpeed + "\n" +
                        "Player 2 speed: " + playerTwoSpeed
        );

        /*checking for property*/
        Entity.Holder<Float> speedHolder = new Entity.Holder<>();
        if(player1.tryGet(UserAppKeys.Speed, speedHolder)){
            System.out.println("Speed=" + speedHolder.value);
        }

        /*custom changing property*/
        changeSpeed(player1, 15, '+');

        if(player1.tryGet(UserAppKeys.Speed, speedHolder)){
            System.out.println("Speed=" + speedHolder.value);
        }

        /*checking non-existence property*/
        Entity.Holder<Float> playerTwoSpeedHolder = new Entity.Holder();
        if(!player2.tryGet(UserAppKeys.Speed, playerTwoSpeedHolder)){
            playerTwoSpeedHolder.value = player2.getOrDefault(UserAppKeys.Speed, 0.0f);
            player2.set(UserAppKeys.Speed, playerTwoSpeedHolder.value);
            System.out.println("Current speed of second player is 0!");
        }

        changeSpeed(player2, 8, '+');

        System.out.println("Changed speed - " + playerTwoSpeedHolder.value);

    }
    /*
    * custom speed changing method
    */
    public static void  changeSpeed(Entity e, float value, char way){
        Entity.Holder<Float> speedHolder = new Entity.Holder<>();
        if(e.tryGet(UserAppKeys.Speed, speedHolder)){
            switch (way){
                case '-' ->{
                    e.set(UserAppKeys.Speed, speedHolder.value - value);
                }
                case '+' -> {
                    e.set(UserAppKeys.Speed, speedHolder.value + value);
                }
                default -> {
                    System.out.println("problem");
                }
            }
        }else{
            System.out.println("prop Speed not selected");
        }

    }
}
