package vn.edu.tinhoc123.gamewithandroidstudio.gameobject;
//chay animation nguoi choi
public class PlayerState {
    public enum State{
        NOT_DICHUYEN,
        STARTED_DICHUYEN,
        IS_DICHUYEN
    }
    private Player player;
    private State state;

    public PlayerState(Player player){
        this.player=player;
        this.state= State.NOT_DICHUYEN;
    }

    public State getState(){
        return state;
    }

    public void update(){
        switch (state){
            case NOT_DICHUYEN:
                if (player.velocityX != 0 || player.velocityY != 0)
                    state = State.STARTED_DICHUYEN;
                break;
            case STARTED_DICHUYEN:
                if (player.velocityX != 0 || player.velocityY != 0)
                    state = State.IS_DICHUYEN;
                break;
            case IS_DICHUYEN:
                if (player.velocityX==0 && player.velocityY==0)
                    state = State.NOT_DICHUYEN;
                break;
            default:
                break;
        }
    }
}
