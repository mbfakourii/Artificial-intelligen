/*
 * Copyright (C) 2016 Naman Dwivedi
 *
 * Licensed under the GNU General Public License v3
 *
 * This is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 */

package ir.mbfakouri.artificialintelligence.DFS_BFS.codes;

import android.app.Activity;
import android.content.DialogInterface;
import androidx.appcompat.app.AlertDialog;
import android.util.Log;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class GraphTraversalAlgorithm extends Algorithm implements Algorithm.DataHandler {

    public static final String TRAVERSE_BFS = "traverse_bfs";
    public static final String TRAVERSE_DFS = "traverse_dfs";
    int goal;
    private Digraph graph;
    private int int_number_try;
    private DirectedGraphVisualizer visualizer;

    public GraphTraversalAlgorithm(DirectedGraphVisualizer visualizer, Activity activity, int goal) {
        this.visualizer = visualizer;
        this.activity = activity;
        this.goal = goal;
        int_number_try = 0;
    }

    @Override
    public void onDataRecieved(Object data) {
        this.graph = (Digraph) data;
    }

    private void agent_function_bfs(int source) {

        sleep2();

        addLog("Traversing the graph with breadth-first search");
        Queue<Integer> queue = new LinkedList<Integer>();

        int numberOfNodes = graph.size();
        addLog("Total number of nodes: " + numberOfNodes);

        addLog("goal: " + goal);
        int[] visited = new int[numberOfNodes + 1];

        int i, element;

        addLog("Starting from source: " + source);
        highlightNode(source);
        visited[source] = 1;
        queue.add(source);
        sleep();


        boolean first = true;

        while (!queue.isEmpty()) {
            element = queue.remove();
            i = element;
            if (first) {
                first = false;
                if (element == goal) {
                    addLog("bfs Find2");
                    return;
                }
            }
            while (i <= numberOfNodes) {
                if (graph.edgeExists(element, i) && visited[i] == 0) {
                    addLog("Going from " + element + " to " + i);
                    highlightNode(i);
                    highlightLine(element, i);
                    queue.add(i);
                    visited[i] = 1;
                    if (i == goal) {
                        addLog("Find");
                        addLog("bfs traversing completed");
                        return;
                    }
                    addLog("i");
                    sleep();

                }
                i++;
            }
        }
        addLog("NOT");


    }

    private void agent_function_dfs(int source) {

        sleep2();
        addLog("Traversing the graph with depth-first search");
        Stack<Integer> stack = new Stack<>();

        int numberOfNodes = graph.size();
        addLog("Total number of nodes: " + numberOfNodes);

        int visited[] = new int[numberOfNodes + 1];
        int element = source;
        int i = source;
        addLog("Starting from source: " + source);
        highlightNode(source);
        visited[source] = 1;
        stack.push(source);
        sleep();
        boolean first = true;
        if (element == goal) {
            addLog("DFS Find2");
            return;
        }

        while (!stack.isEmpty()) {
            element = stack.peek();
            i = element;
            if (first) {
                first = false;
                if (element == goal) {
                    addLog("DFS Find");
                    return;
                }
            }
            while (i <= numberOfNodes) {
                if (graph.edgeExists(element, i) && visited[i] == 0) {
                    addLog("Going from " + element + " to " + i);
                    highlightNode(i);
                    highlightLine(element, i);
                    stack.push(i);
                    visited[i] = 1;
                    element = i;

                    if (i == goal) {
                        addLog("DFS Find");
                        return;
                    }

                    i = 1;
                    sleep();
                    addLog("i");

                    continue;
                }
                i++;
            }
            stack.pop();
        }
        addLog("NOT");

    }


    private void highlightNode(final int node) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                visualizer.highlightNode(node);
            }
        });
    }

    private void highlightLine(final int start, final int end) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                visualizer.highlightLine(start, end);
            }
        });
    }

    @Override
    public void onMessageReceived(String message) {
        if (message.equals(TRAVERSE_BFS)) {
            startExecution();
            agent_function_bfs(graph.getRoot());
        } else if (message.endsWith(TRAVERSE_DFS)) {
            startExecution();
            agent_function_dfs(graph.getRoot());
        }
    }

    public void setData(final Digraph g) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                visualizer.setData(g);
            }
        });
        start();
        prepareHandler(this);
        sendData(g);
    }

    private void addLog(String value) {
        Log.i("TAG", value);

        if (value.contains("Find2")) {
            show_message(-1, true);
            return;
        }

        if (value.contains("Find")) {
            show_message(int_number_try, true);
            return;
        }

        if (value.contains("NOT")) {
            show_message(0, false);
            return;
        }

        if (value.equals("i")) {
            int_number_try++;
        }
    }


    private void show_message(int int_number_ok, boolean bol_find) {
        Log.i("TAG", "--------------------------");
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(activity);
        builder.setTitle("توجه");

        if (bol_find) {
            if (int_number_ok == -1) {
                builder.setMessage("هدف با " + 0 + " حرکت پیدا شد !");
            } else {
                builder.setMessage("هدف با " + (int_number_ok + 1) + " حرکت پیدا شد !");
            }
        } else {
            builder.setMessage("هدف پیدا نشد !");
        }

        builder.setPositiveButton("باشه", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                activity.finish();
            }
        });

        builder.setNegativeButton("", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                activity.finish();
            }
        });

        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {

                activity.finish();
            }
        });

        builder.show();

    }
}
