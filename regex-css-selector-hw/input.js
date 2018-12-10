regExps = {
"exercise_1": /[A-Z]{1}[a-z]+/,
"exercise_2": /(088[1-9]{7})/,
"exercise_3": /[^10]+/,
"exercise_4": /^[a-z][\w.]{2,15}$/,
"exercise_5": /1[0-5]\d{1}[^1]|999/,
"exercise_6": /class=['\\"]"*[\w ]+\\?['"]/
};
cssSelectors = {
"exercise_1": "item>java",
"exercise_2": "different>java",
"exercise_3": ".class1 > .class1.class2",
"exercise_4": "css>*:nth-child(3)",
"exercise_5": "tag > java + .class1",
"exercise_6": "item > item > item > item > item",
"exercise_7": "#diffId2>*:nth-child(2)",
"exercise_8": "#someId"
};
