package com.example.caleb.pet;

/**
 * Created by Caleb on 3/8/2018.
 */
/**********************************************************
 *Alien()
 *
 * Alien set up positions and velocity
 *
 *
 ***********************************************************/
public class Alien {
        private int mX, mY, velocity;

        public int getmX() {
            return mX;
        }

        public void setmX(int mX) {
            this.mX = mX;
        }

        public int getmY() {
            return mY;
        }

        public void setmY(int mY) {
            this.mY = mY;
        }

        public int getVelocity() {
            return velocity;
        }

        public void setVelocity(int velocity) {
            this.velocity = velocity;
        }



        public void move(int destX, int destY){
            int distanceX = destX -mX;
            int distanceY = destY - mY;
            mX += distanceX/velocity;
            mY += distanceY/velocity;
        }
}

