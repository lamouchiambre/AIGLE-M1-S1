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