package model.pieces.sidekicks;

import exceptions.UnallowedMovementException;
import model.game.Direction;
import model.game.Game;

public class SideKickP1 extends SideKick {
	@Override
	public void moveDownLeft() throws UnallowedMovementException {
		throw new UnallowedMovementException("SideKick can't move in this direction",this, Direction.DOWNLEFT);
	}

	@Override
	public void moveDown() throws UnallowedMovementException {
		throw new UnallowedMovementException("Tech can't move in this direction",this, Direction.DOWN);
	}

	@Override
	public void moveDownRight() throws UnallowedMovementException {
		throw new UnallowedMovementException("Tech can't move in this direction",this, Direction.DOWNRIGHT);
	}

	public SideKickP1(Game game, String name) {
		super(game.getPlayer1(), game, name);
	}
	public String toString() {
		return "SideKick 1";
	}

	
}
