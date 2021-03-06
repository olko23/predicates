(ns predicates)

(defn sum-f [f g x]
  (+ (f x) (g x)))

(defn less-than [n]
  (fn [x] (< x n)))

(defn equal-to [n]
  (fn [x] (== x n)))

(defn set->predicate [a-set]
  (fn [x] (contains? a-set x)))

(defn pred-and [pred1 pred2]
  (fn [x] (and (pred1 x)
               (pred2 x))))

(defn pred-or [pred1 pred2]
  (fn [x] (or (pred1 x)
              (pred2 x))))

(defn whitespace? [character]
  (Character/isWhitespace character))

(defn blank? [string]
  (every? whitespace? string))

(defn has-award? [book award]
  (contains? (:awards book) award))

(defn HAS-ALL-THE-AWARDS? [book awards]
  (let [book-has-award? (partial has-award? book)]
    (every? true? (map book-has-award? awards))))

(defn my-some [pred a-seq]
  (let [truthy? (complement false?)]
    (first (filter truthy? (map pred a-seq)))))

(defn my-every? [pred a-seq]
  (let [not-pred? (complement pred)]
    (not (my-some not-pred? a-seq))))

(defn prime? [n]
  (let [divides? (fn [x y] (= (mod x y) 0))
        divides-n? (partial divides? n)]
    (not (my-some divides-n? (range 2 n)))))

;^^





