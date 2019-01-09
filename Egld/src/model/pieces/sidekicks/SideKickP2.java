package model.pieces.sidekicks;

import exceptions.UnallowedMovementException;
import model.game.Direction;
import model.game.Game;

public class SideKickP2 extends SideKick {

	public SideKickP2(Game game, String name) {
		super(game.getPlayer2(), game, name);
	}
	public void moveUpLeft() throws UnallowedMovementException {
		throw new UnallowedMovementException("SideKick can't move in this direction",this, Direction.UPLEFT);
	}

	@Override
	public void moveUp() throws UnallowedMovementException {
		throw new UnallowedMovementException("Tech can't move in this direction",this, Direction.UP);
	}

	@Override
	public void moveUpRight() throws UnallowedMovementException {
		throw new UnallowedMovementException("Tech can't move in this direction",this, Direction.UPRIGHT);
	}
	public String toString() {
		return "SideKick 2";
	}

}
