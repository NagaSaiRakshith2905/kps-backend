package com.capgemini.kpsbackend.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ListAllPaths {
    public static void main(String[] args) {
        ListAllPaths listAllPaths = new ListAllPaths();

        List<Map<String, List<String>>> list = new ArrayList<>();
        list.add(Map.of("Node1", List.of("Node2", "Node3", "Node6")));
        list.add(Map.of("Node2", List.of("Node1", "Node4", "Node5")));
        list.add(Map.of("Node3", List.of("Node1", "Node4", "Node5")));
        list.add(Map.of("Node4", List.of("Node2", "Node3", "Node6")));
        list.add(Map.of("Node5", List.of("Node2", "Node3", "Node6")));
        list.add(Map.of("Node6", List.of("Node1", "Node4", "Node5")));

//        list.forEach(System.out::println);
//        List<List<String>> strings = list.stream().filter(stringListMap -> stringListMap.keySet().toString().contains((CharSequence) "Node1")).map(stringListMap -> stringListMap.get("Node1")).collect(Collectors.toList());
//        System.out.println(strings.get(0));

        System.out.println(listAllPaths.getPaths(list, "Node1", "Node6"));


    }

    private List<List<String>> getPaths(List<Map<String, List<String>>> list, String src, String dst) {
        List<List<String>> result = new ArrayList<>();
        List<String> path = new ArrayList<>();
        List<String> visited = new ArrayList<>();
        calculate(list, result, path, src, dst, visited);
        return result;
    }

    private void calculate(List<Map<String, List<String>>> list, List<List<String>> result, List<String> path, String src, String dst, List<String> visited) {
        path.add(src);
        System.out.println(src);
        System.out.println("Visited : "+visited);
        if (src == dst) {
            visited.remove(visited.size() - 1);
//            System.out.println("removed : " + src);
            result.add(new ArrayList<>(path));
        } else {
            if (!visited.contains(src)) {
                List<List<String>> strings = list.stream().filter(stringListMap -> stringListMap.keySet().toString().contains((CharSequence) src)).map(stringListMap -> stringListMap.get(src)).collect(Collectors.toList());
                visited.add(src);
//                System.out.println("added : " + src);
                for (String v : strings.get(0))
                    calculate(list, result, path, v, dst, visited);
            }
        }
        path.remove(path.size() - 1);
    }


}
