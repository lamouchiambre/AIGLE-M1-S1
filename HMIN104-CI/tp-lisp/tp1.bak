#lang racket

; 1. lambda-expressions
;((lambda (x) (+ (* 2 x) 3)) 4) 
;((lambda (x y) (* (+ x 2) (+ y 6))) 7 8)
;((lambda (v) ((lambda (x) (* 3 x)) (+ v 2))) 8)
;((lambda (v) ((lambda (x) (* v x)) (+ v 2))) 8)
;((lambda (v) ((lambda (v) (* 3 v)) (+ v 2))) 8)
;((lambda (x y z) (+ (* x y) (* y z))) 2 3 4)
;((lambda (x y) (* x x y y)) 4 2)
;((lambda (x) (* x x 2)) 4)
;(lambda (x) (* x x 2))

; 2. fonctions globales
(define (f x) (+ 3 x))
(define (g x) (* 5 (f (+ x 2))))
;(g 8)

(define (fact2 n)
  (cond [(> n 1) (* n (fact2 (- n 1)))]
        [#t n]))

(define (fact n)
  (if (> n 1) (* n (fact (- n 1)))
      n))
;(fact 4)

(define (fibo n) ; Complexité : O(n^2)
  (if (< n 2) 1
      (+ (fibo (- n 1)) (fibo (- n 2)))))
;(fibo 5)

; 3. les listes et les cellules

;(cons 1 (cons 2 (cons 3 (cons 4 '()))))

; nb de cellules
; (1 2 3 4) -> 4
; (1 (2 3) 4) -> 4
; (1 (2) (3) 4) -> 5

; 4. fonctions sur les listes plates

(define list1 '(1 2 3 1 4))

(define (member x l)
  (cond [(equal? l '()) '()]
        [(equal? (car l) x) l]
        [#t (member x (cdr l))] ))

(define (lengh l)
  (cond [(equal? l '()) 0]
        [#t (+ 1 (lengh (cdr l)))] ))

(define (last l)
  (cond [(equal? l '()) '()]
        [(equal? (cdr l) '()) (car l)]
        [#t (last (cdr l))] ))

(define (makelist n)
  (cond [(= n 0) '()]
        [#t (cons n (makelist (- n 1)))] ))

(define (copylist l)
  (cond [(equal? l '()) '()]
        [#t (cons (car l) (cdr l))] ))

(define (remove x l)
  (cond [(equal? l '()) '()]
        [(equal? (car l) x) (remove x (cdr l))]
        [#t (cons (car l) (remove x (cdr l)))] ))

(define (remove2 x l)
  (cond [(equal? l '()) '()]
        [(equal? (car l) x) (cdr l)]
        [#t (cons (car l) (remove2 x (cdr l)))] ))

(define (append l1 l2)
  (cond [(equal? l1 '()) l2]
        [(equal? l2 '()) l1]
        [#t (cons (car l1) (append (cdr l1) l2))] ))

(define (adjoin x l)
  (cond [(not (equal? (member x l) '())) l]
        [#t (cons x l)] ))

; 5. fonctions sur les arbres binaires

(define t0 '(1 2 3 4))
(define t1 '(1 (2 3) 4))
(define t2 '(1 (2 3) 4 ((5 6) 1)))

(define (size t)
  (cond [(equal? t '()) 0]
        [(pair? (car t)) (+ (size (car t)) (size (cdr t)))]
        [(pair? (cdr t)) (+ 1 (size (cdr t)))]
        [#t (+ 1 (size (cdr t)))] ))

(define (copytree t)
  (cond [(equal? t '()) '()]
        [(pair? (car t)) (list (car t) (copytree (cdr t)))]
        [#t (cons (car t) (cdr t))] ))

(define (subst x y t)
  (cond [(equal? t '()) '()]
        [(equal? (car t) x) (cons y (subst x y (cdr t)))]
        ;[(equal? (cdr t) x) (cons (subst x y (car t)) y)]
        [#t (cons (car t) (subst x y (cdr t)))] ))

(define (tree-leaves t)
  (cond [(equal? t '()) '()]
        [(not (pair? (car t))) (cons (car t) (tree-leaves (cdr t)))]
        [#t (append (tree-leaves (car t)) (tree-leaves (cdr t)))] ))

; 6. test d'égalité

(define (test-egalite1 n)
  (print (= (fact n) (fact n))) )

;(define (test-egalite2 n)                  ; "eq" non reconnu
 ;(print (eq (fact n) (fact n))) )

;(define (test-egalite3 n)                  ; "eql" non reconnu
  ;(print (eql (fact n) (fact n))) )

(define (test-egalite4 n)
  (print (equal? (fact n) (fact n))) )

; 7. récusion terminale et enveloppée

;(define (copylist2 l)
;  (cond [(equal? l) l]))