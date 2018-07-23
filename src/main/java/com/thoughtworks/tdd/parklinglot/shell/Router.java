package com.thoughtworks.tdd.parklinglot.shell;

import com.thoughtworks.tdd.parklinglot.shell.controller.BaseController;
import com.thoughtworks.tdd.parklinglot.shell.io.Request;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Router {

    private final String initRoutePath;
    private String currentPath;
    private Map<String, BaseController> routeMaps = new HashMap<>();

    public Router(String initStatus) {
        this.initRoutePath = initStatus;
        this.currentPath = initStatus;
    }

    public void launch() {
        routeMaps.get(this.initRoutePath).process();
    }

    public void registerRoute(String route, BaseController processor) {
        routeMaps.put(route, processor);
    }

    public void processRequest(Request request) {

        String routePath = buildLocateRoutePath(request);
        if(routePath.contains("root/1")){
            routePath="main";
        }
        if(routePath.contains("root/2")){
            routePath="manage";
        }
//        if(routePath.contains("*")){
//            routePath="root";
//        }
        //String forwardRouteRule = routeMaps.get(routePath).process();
        String rootRouteRule = routeMaps.get(routePath).process();
        currentPath = routePath;

        if (rootRouteRule != null && rootRouteRule.contains("root")) {
            currentPath = buildForwardRoutePath(rootRouteRule);
        }
//        if (forwardRouteRule != null && forwardRouteRule.contains("forward:")) {
//            currentPath = buildForwardRoutePath(forwardRouteRule);
//        }
    }
    private String buildForwardRoutePath(String rootRouteRule) {
        //String rootRoute = rootRouteRule.split(":")[1];
        routeMaps.get(rootRouteRule).process();
        return rootRouteRule;
    }


//    private String buildForwardRoutePath(String forwardRouteRule) {
//        String forwardRoute = forwardRouteRule.split(":")[1];
//        routeMaps.get(forwardRoute).process();
//        return forwardRoute;
//    }

    private String buildLocateRoutePath(Request request) {

        String subPath = request.getCommand().isEmpty() ? "" : "/" + translateRequestInput(request);
        return currentPath + subPath;
    }

    private String translateRequestInput(Request request) {
        if (Arrays.asList("1", "2").contains(request.getCommand())) {
            return request.getCommand();
        } else {
            return "*";
        }
    }
}
