/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dsaprojectone;

/**
 *
 * @author Administrator
 */
public class node {
    int student;
    int course;
    node right;
    node down;
    node end;
    node(){}
    node(int student,int course){
        this.student=student;
        this.course=course;
    }
    node(node right,node down){
        this.right=right;
        this.down=down;
    }
    
}
