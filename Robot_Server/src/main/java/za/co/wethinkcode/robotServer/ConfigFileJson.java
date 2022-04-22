package za.co.wethinkcode.robotServer;

import za.co.wethinkcode.robotServer.World.SquareObstacle;

public class ConfigFileJson {

        GridJson gridSize;
        int visibility;
        SquareObstacle[] obstacles;
        int shieldRepairTime;
        int reloadTime;
        int maxShieldStrength;

    public GridJson getGridSize() {
        return gridSize;
    }

    public int getVisibility() {
        return visibility;
    }

    public SquareObstacle[] getObstacles() {
        return obstacles;
    }

    public int getShieldRepairTime() {
        return shieldRepairTime;
    }

    public int getReloadTime() {
        return reloadTime;
    }

    public int getMaxShieldStrength() {
        return maxShieldStrength;
    }

    public ConfigFileJson(GridJson gridSize, int visibility, SquareObstacle[] obstacles,
                          int shieldRepairTime, int reloadTime, int maxShieldStrength) {
            this.gridSize = gridSize;
            this.visibility = visibility;
            this.obstacles = obstacles;
            this.shieldRepairTime = shieldRepairTime;
            this.reloadTime = reloadTime;
            this.maxShieldStrength = maxShieldStrength;
        }


    static class GridJson{
        int x;
        int y;

        public GridJson(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    }

