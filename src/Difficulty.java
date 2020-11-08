public class Difficulty {
    
    public static Integer currentSpeed;
    public static Integer lastFrameSpeedWasIncreased;

    public static void setSpeed(Integer speed) {
        currentSpeed = speed;
        lastFrameSpeedWasIncreased = GameState.currentFrame;
    }

    public static void check() {

        if (lastFrameSpeedWasIncreased + Config.DIFFICULTY <= GameState.currentFrame && currentSpeed > 0) {
            // Rise the difficulty

            setSpeed(currentSpeed-1);

            if (Config.DEBUG)
                System.out.println("Difficulty increased to "+currentSpeed);
        }

    }

}
