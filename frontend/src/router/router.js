import Vue from "vue";
import VueRouter from "vue-router";
Vue.use(VueRouter);

import Home from "../components/Info/Home.vue";
import Learn from '../components/Info/Learn.vue';
import Student from '../components/Info/Student.vue';
import Information from '../components/Info/Information.vue'
import Login from '../view/Login.vue';
import Index from '../components/Index.vue';

import User from "../components/Info/User.vue";
import EditImf from "../components/Info/EditImf.vue";
import work1 from "../components/Info/work.vue";
import expe from "../components/Info/expe.vue";
import exam from "../components/Info/exam.vue";

import stuWork from '../components/Work/stuWork.vue';
import Work from '../components/Work/Work.vue';
import stuExperiment from '../components/Work/stuExperiment.vue';
import stuExamination from '../components/Work/stuExamination.vue';
import Experiment from '../components/Work/Experiment.vue';
import Examination from '../components/Work/Examination.vue';

import one from '../components/Create/1.vue'
import oned from '../components/Create/1-d.vue'
import onedu from '../components/Create/1-du.vue'
import onej from '../components/Create/1-j.vue'
import onep from '../components/Create/1-p.vue'
import onet from '../components/Create/1-t.vue'
import CreateCourse from '../components/Create/CreateCourse.vue'
import testa from '../components/Create/test-auto.vue'
import testh from '../components/Create/test-hand.vue'
import createTest from '../components/Create/createTest.vue'
import createWork from '../components/Create/createWork.vue'
import workh from '../components/Create/work-hand.vue'
import worka from '../components/Create/work-auto.vue'
import createLib from '../components/Create/createLib.vue'
import libcreate from '../components/Create/libcreate.vue'
import viewWork from '../components/Create/viewWork.vue'
import viewExp from '../components/Create/viewExp.vue'
import viewExa from '../components/Create/viewExa.vue'

import TeacherInfo from '../components/Info/TeacherInfo.vue'
import Marking from '../components/Work/Marking.vue'
import MarkingList from '../components/Work/MarkingList.vue'
import mWork from '../components/Work/mWork.vue'
import mExperiment from '../components/Work/mExperiment.vue'
import mExamination from '../components/Work/mExamination.vue'
import Class from '../components/Info/Class.vue'

const routes = [
    {
        path: '/',
        component: Index,
        children: [
            {
                path: '/',
                component: Learn
            },
            {
                path: '/student',
                component: Student
            },
            {
                path: '/imfold',
                component: Information
            },

            {
                path: '/user',
                component: User
            },
            {
                path: '/expe',
                component: expe
            },
            {
                path: '/work1',
                component: work1
            },
            {
                path: '/exam',
                component: exam,
            },
            {
                path: '/imf',
                component: EditImf,
            },

            {
                path: '/stuWork',
                component: stuWork
            },
            {
                path: '/Work',
                component: Work
            },
            {
                path: '/stuExperiment',
                component: stuExperiment
            },
            {
                path: '/stuExamination',
                component: stuExamination
            },
            {
                path: '/Experiment',
                component: Experiment
            },
            {
                path: '/Examination',
                component: Examination
            },
            {
                path: '/one',
                component: one,
                redirect: 'oned',
                children: [
                    {
                        path: '/oned',
                        component: oned
                    },
                    {
                        path: '/onedu',
                        component: onedu
                    },
                    {
                        path: '/onej',
                        component: onej
                    },
                    {
                        path: '/onep',
                        component: onep
                    },
                    {
                        path: '/onet',
                        component: onet
                    }
                ]
            },
            {
                path: 'createCourse',
                component: CreateCourse
            },
            {
                path: '/createTest',
                component: createTest,

            },
            {
                path: '/createWork',
                component: createWork,
            },
            {
                path: 'workh',
                component: workh
            },
            {
                path: 'worka',
                component: worka
            },
            {
                path: '/createLib',
                component: createLib,
            },
            {
                path: 'libcreate',
                component: libcreate
            }
            ,
            {
                path: 'testa',
                component: testa
            },
            {
                path: 'testh',
                component: testh
            },
            { path: '/teacher', name: 'teacher', component: TeacherInfo },
            {
                path: '/markingList', name: 'MarkingList', component: MarkingList, redirect: 'mWork', children: [
                    {
                        path: '/mWork',
                        component: mWork
                    },
                    {
                        path: '/mExperiment',
                        component: mExperiment
                    },
                    {
                        path: '/mExamination',
                        component: mExamination
                    },
                    {
                        path: '/marking',
                        component: Marking
                    }
                ]
            },
            { path: '/Class', name: 'Class', component: Class },
            { path: '/viewWork', name: 'viewWork', component: viewWork },
            { path: '/viewExp', name: 'viewExp', component: viewExp },
            { path: '/viewExa', name: 'viewExa', component: viewExa },
        ]
    },
    {
        path: '/login',
        component: Login
    },
    {
        path: '/home',
        component: Home
    },
]

const router = new VueRouter({
    routes
})
export default router