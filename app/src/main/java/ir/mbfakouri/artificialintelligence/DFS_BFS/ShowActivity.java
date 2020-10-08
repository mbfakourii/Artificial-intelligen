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

package ir.mbfakouri.artificialintelligence.DFS_BFS;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.FrameLayout;

import ir.mbfakouri.artificialintelligence.DFS_BFS.codes.Algorithm;
import ir.mbfakouri.artificialintelligence.DFS_BFS.codes.AlgorithmVisualizer;
import ir.mbfakouri.artificialintelligence.DFS_BFS.codes.Digraph;
import ir.mbfakouri.artificialintelligence.DFS_BFS.codes.DirectedGraphVisualizer;
import ir.mbfakouri.artificialintelligence.DFS_BFS.codes.GraphTraversalAlgorithm;
import ir.mbfakouri.artificialintelligence.R;

/**
 * Created by naman on 03/06/16.
 */
public class ShowActivity extends AppCompatActivity {

    Algorithm algorithm;
    FrameLayout container;
    String startCommand = Algorithm.DFS;


    String str_tag;
    String str_type;
    int str_number_search;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);



//get PutExtra
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                str_type = "DFS";
                str_tag = "1";
                str_number_search = 4;
            } else {
                str_type = extras.getString("TYPE");
                str_tag = extras.getString("TAG");
                str_number_search = extras.getInt("NUMBER_SEARCH");
            }
        } else {
            str_type = (String) savedInstanceState.getSerializable("TYPE");
            str_tag = (String) savedInstanceState.getSerializable("TAG");
            str_number_search = (int) savedInstanceState.getSerializable("NUMBER_SEARCH");
        }


        container = (FrameLayout) findViewById(R.id.container);


        if (str_type.equals("BFS")) {
            startCommand = GraphTraversalAlgorithm.TRAVERSE_BFS;
            setupFragment(Algorithm.BFS, str_number_search);
        } else {
            startCommand = GraphTraversalAlgorithm.TRAVERSE_DFS;
            setupFragment(Algorithm.DFS, str_number_search);
        }

    }

    public void setupFragment(String algorithmKey, int goal) {

        assert algorithmKey != null;

        final AlgorithmVisualizer visualizer;


        visualizer = new DirectedGraphVisualizer(this);
        container.addView(visualizer);
        algorithm = new GraphTraversalAlgorithm((DirectedGraphVisualizer) visualizer, this, goal);

        ((GraphTraversalAlgorithm) algorithm).setData(createDirectedGraph(str_tag));


        algorithm.setStarted(false);


        //start
        if (!algorithm.isStarted()) {
            algorithm.sendMessage(startCommand);
        } else {
            if (algorithm.isPaused()) {
                algorithm.setPaused(false);
            } else {
                algorithm.setPaused(true);
            }
        }


    }


    public static Digraph createDirectedGraph(String str_tag) {
        int int_tag = Integer.parseInt(str_tag);
        if (int_tag == 1) {
            Digraph graph = new Digraph();
            graph.add(0, 1);
            graph.add(0, 2);
            graph.add(1, 3);
            graph.add(3, 7);
            graph.add(3, 8);
            graph.add(1, 4);
            graph.add(4, 9);
            graph.add(4, 10);
            graph.add(2, 5);
            graph.add(2, 6);

            double[][] array = {
                    {0, 2, 6, 5, 1, 4, 10, 9, 3, 8, 7}, //nodes
                    {1, 5, -1, -1, 3, 9, -1, -1, 7, -1, -1},//left child
                    {2, 6, -1, -1, 4, 10, -1, -1, 8, -1, -1}, //right child
                    {0, 1.5, 2.5, 1, 1.5, 0.5, 0, 1, 2.5, 2, 3}, //number of left/right nodes from root in horizontal
                    {0, 1, 2.5, 2.5, 1, 2, 3, 3, 2, 3, 3}, //number of left/right nodes from root in vertical
                    {-1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1} //is the node left of the root?
            };
            graph.setDirectedArray(array);
            return graph;
        } else if (int_tag == 2) {
            Digraph graph = new Digraph();
            graph.add(1, 2);
            graph.add(1, 3);
            graph.add(2, 4);
            graph.add(2, 5);
            graph.add(5, 7);
            graph.add(5, 8);
            graph.add(3, 6);
            graph.add(6, 9);

//            {0, 2, 6, 5, 1, 4, 10, 9, 3, 8, 7}, //nodes
            double[][] array = {
                    {1, 3, 6, 9, 2, 5, 8, 7, 4}, //nodes
                    {2, -1, 9, -1, 4, 7, -1, -1, -1},//left child
                    {3, 6, -1, -1, 5, 8, -1, -1, -1}, //right child
                    {0, 1, 2, 1, 1, 0, 0, 1, 2}, //number of left/right nodes from root in horizontal
                    {0, 1, 2, 3, 1, 2, 3, 3, 2}, //number of left/right nodes from root in vertical
                    {0, 0, 0, 0, 1, 1, 1, 1, 1} //is the node left of the root?
            };
            graph.setDirectedArray(array);
            return graph;
        } else if (int_tag == 3) {
            Digraph graph = new Digraph();
            graph.add(1, 2);
            graph.add(1, 3);
            graph.add(2, 4);
            graph.add(2, 5);
            graph.add(4, 8);
            graph.add(4, 9);
            graph.add(5, 10);
            graph.add(5, 11);
            graph.add(3, 6);
            graph.add(3, 7);
            graph.add(6, 12);
            graph.add(6, 13);
            graph.add(7, 14);
            graph.add(7, 15);

            double[][] array = {
                    {1, 3 , 7, 15, 14, 6, 13, 12 , 2 , 5 , 11 , 10 , 4 , 9 , 8 }, //nodes
                    {2, 6, 14, -1, -1,12, -1, -1,  4 ,10 ,-1  , -1 , 8 , -1, -1},//left child
                    {3, 7, 15, -1, -1,13, -1, -1,  5 ,11 ,-1  , -1 , 9 , -1, -1}, //right child
                    {0, 2 , 3, 4 , 3 , 1,  2,  1,  2 ,1  , 1  , 2  , 3 , 3 ,  4}, //number of left/right nodes from root in horizontal
                    {0, 1 , 2, 3 , 3 , 2,  3,  3,  1 ,2  , 3  , 3  , 2 , 3 ,  3}, //number of left/right nodes from root in vertical
                    {0, 0 , 0, 0 , 0 , 0, 0 , 0 ,  1 ,1  , 1  , 1  , 1 , 1 ,  1} //is the node left of the root?

            };
            graph.setDirectedArray(array);
            return graph;
        } else {
            return createDirectedGraph("1");
        }
    }
}

