let a=[1,2,3,4,5,6];
//shift->pop start elem
//unshift-> push at start 
//push-> push at last
//pop-> pop last elem
//indexOf->find the index of a particular elem

a.splice(2,2,1,1);
let b=a.slice(1,3);
console.log(a);
console.log(b);
