package com.tu.streetescape;

import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.math.Vector2;

public class SteerableEnemy implements Steerable<Vector2>{
	
	private float maxLinearSpeed;
	private float maxLinearAcceleration;
	private float maxAngularSpeed;
	private float maxAngularAcceleration;
	
	@Override
	public float getMaxLinearSpeed() {
		return maxLinearSpeed;
	}

	@Override
	public void setMaxLinearSpeed(float maxLinearSpeed) {
		this.maxLinearSpeed = maxLinearSpeed;
	}

	@Override
	public float getMaxLinearAcceleration() {
		return maxLinearAcceleration;
	}

	@Override
	public void setMaxLinearAcceleration(float maxLinearAcceleration) {
		this.maxLinearAcceleration = maxLinearAcceleration;		
	}

	@Override
	public float getMaxAngularSpeed() {
		return maxAngularSpeed;
	}

	@Override
	public void setMaxAngularSpeed(float maxAngularSpeed) {
		this.maxAngularSpeed = maxAngularSpeed;
	}

	@Override
	public float getMaxAngularAcceleration() {
		return maxAngularAcceleration;
	}

	@Override
	public void setMaxAngularAcceleration(float maxAngularAcceleration) {
		this.maxAngularAcceleration = maxAngularAcceleration;
	}

	@Override
	public Vector2 getPosition() {
		return null;
	}

	@Override
	public float getOrientation() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Vector2 getLinearVelocity() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public float getAngularVelocity() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getBoundingRadius() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isTagged() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setTagged(boolean tagged) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Vector2 newVector() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public float vectorToAngle(Vector2 vector) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Vector2 angleToVector(Vector2 outVector, float angle) {
		// TODO Auto-generated method stub
		return null;
	}

}
