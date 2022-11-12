package csc340.caffeinatedfoxes.puzzle;

/**
 *
 * @author sofie
 */
public class ClimbingRoute {
    private String routeName;
    private String routeDifficulty;
    private String routeLocation;
    private String routeTips;

    public ClimbingRoute(String routeName, String routeDifficulty, String routeLocation) {
        this.routeName = routeName;
        this.routeDifficulty = routeDifficulty;
        this.routeLocation = routeLocation;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public String getRouteDifficulty() {
        return routeDifficulty;
    }

    public void setRouteDifficulty(String routeDifficulty) {
        this.routeDifficulty = routeDifficulty;
    }

    public String getRouteLocation() {
        return routeLocation;
    }

    public void setRouteLocation(String routeLocation) {
        this.routeLocation = routeLocation;
    }

    public String getRouteTips() {
        return routeTips;
    }

    public void setRouteTips(String routeTips) {
        this.routeTips = routeTips;
    }

}