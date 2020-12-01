;; ((lambda (x) (+ (* 2 x) 3)) 4)
;; ((lambda (x y) (* (+ x 2) (+ y 6))) 7 8)		
;; ((lambda (v) ((lambda (x) (* 3 x)) (+ v 2))) 8) 		
;; ((lambda (v) ((lambda (x) (* v x)) (+ v 2))) 8) 		
;; ((lambda (v) ((lambda (v) (* 3 v)) (+ v 2))) 8)		
;; ((lambda (x y) (* x x y y)) 4)	
;; ((lambda (x) (* x x 2)) 4 5) 	
;; (lambda (x) (* x x 2)) 		

(defun f (x) (+ 3 x))
(defun g (v) (* 5 (f (+ v 2))))

(defun toto(x) (+ 2 x))

(defun fact (n) (if (= n 0) 1 (* n (fact (- n 1)))))

(defun fibo (n) (if ( <= n 1) 1 (+ (fibo (- n 1)) (fibo (- n 2)))))

(defun member (x l) (if (atom l) nil (if eql x (car l)) l (member x (cdr l)))))

(defun mylength (l) (if (atom l) 0 (+ 1 (mylength (cdr l)))))

(defun mylast (l) (if (eq NIL (CDR l)) (CAR l) (mylast (CDR l))))

(defun monmakelist (n) (if (equal 0 n) '(0) (cons n (monmakelist (- n 1)))))

(defun monmakelistcr (a) (deAaB 0 a))

(defun deAaB (a b) (if (equal a b) (list a) (cons a (deAaB (+ 1 a) b))))

(defun copylist (l) (if (atom l) l (cons (car l) (copylist (cdr l)))))

(defun copylist (l) (if (atom l) l (cons (copylist (car l)) (copylist (cdr l)))))

(defun nremove (x l)
    (if (atom l) l
        (if (eql x (car l)) (nremove x (cdr l))
            (progn (setf (cdr l) (nremove x (cdr l))) l))))

(defun nremovebis (x l)
   (if (atom l) l
        (if (eql x (car l)) (cdr l)
            (cons (car l) (nremovebis x (cdr l))))))

(defun myappend (l1 l2)
    (if (equal (cdr l1) nil) (append l1 l2)
        (myappend (cdr l1) l2)))

(defun nadjoin (x l)
    (if (eql x (car l)) l
        (if (equal (cdr l) nil) (append l (list x))
            (cons (car l) (nadjoin x (cdr l))))))
